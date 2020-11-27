package com.example.medic.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.medic.Activity.Home;
import com.example.medic.Adapters.MedicinesAdapter;
import com.example.medic.Api_Interfaces.MedicinesInterface;
import com.example.medic.R;
import com.example.medic.Responses.MedicineListResponse;
import com.example.medic.Responses.MedicineDTO;
import com.example.medic.RetrofitClient.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MedicineFragment extends Fragment {

    RecyclerView mRecyclerView;
    ProgressBar progressBar;

    private MedicinesAdapter medicinesAdapter;
    private RetrofitClient retrofitClient;
    private int pageNumber = 0;
    private MedicinesInterface medicineInterface;
    private MedicineListResponse medicineListResponse;
    private LinearLayout medicineSearch;
    private EditText searchText;
    private LinearLayoutManager layoutManager;
    private int scrollState;
    private int scrollOnY;
    private Long categoryId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);

        medicineSearch = (LinearLayout) view.findViewById(R.id.med_search);
        searchText = (EditText) view.findViewById(R.id.search_product_name);

        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.item_recyclerview);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                //  super.onScrollStateChanged(recyclerView, newState);
                scrollState = newState;
                if(scrollState == 0) {
                    if(scrollOnY>0) {
//
                        pageNumber = ++pageNumber;
                        renderMedicineList(pageNumber, categoryId, null);
                    }
//
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollOnY=dy;
            }

        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        categoryId = (getArguments().getLong("CatID"));
        String clickid = (getArguments().getString("ClickID"));

        if (clickid.equals("Category")) {
            medicineSearch.setVisibility(View.GONE);
            renderMedicineList(pageNumber, categoryId, null);
        } else {

            searchText.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    if (s.length() != 0)

                        renderMedicineList(pageNumber, null, s.toString());
                }
            });


        }

        return view;
    }

    private void renderMedicineList(int pageNumber, Long categoryId, String medicineName) {


        try {
            retrofitClient = RetrofitClient.getInstance();
            medicineInterface = retrofitClient.getRetrofit().create(MedicinesInterface.class);

            progressBar.setVisibility(View.VISIBLE);

            Call<MedicineListResponse> call = medicineInterface.getMedicineList(retrofitClient.getJwtToken(), pageNumber, categoryId, medicineName);

            call.enqueue(new Callback<MedicineListResponse>() {
                @Override
                public void onResponse(Call<MedicineListResponse> call, Response<MedicineListResponse> response) {

                    // loadingBar.dismiss();

                    medicineListResponse = response.body();
                    medicinesAdapter = new MedicinesAdapter(getActivity(), (ArrayList<MedicineDTO>) medicineListResponse.getMedicines());


                    //  categoryAdapter = new CategoryAdapter(data);

                    mRecyclerView.setAdapter(medicinesAdapter);
                    progressBar.setVisibility(View.GONE);


                    //   Toast.makeText(SignUp.this,registerUserResponse.getResponseMessage(),Toast.LENGTH_LONG).show();


                }

                @Override
                public void onFailure(Call<MedicineListResponse> call, Throwable t) {
                    // loadingBar.dismiss();
                    System.out.println("Failed");
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Home activity = (Home) getActivity();
        if (activity != null) {
            activity.showBackButton();
            activity.hideDrawerButton();
        }

    }


}