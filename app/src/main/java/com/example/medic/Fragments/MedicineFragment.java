package com.example.medic.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.medic.Activity.Medicine;
import com.example.medic.Adapters.MedicinesAdapter;
import com.example.medic.Api_Interfaces.MedicinesInterface;
import com.example.medic.R;
import com.example.medic.Responses.MedicineListResponse;
import com.example.medic.Responses.MedicineResponse;
import com.example.medic.RetrofitClient.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MedicineFragment extends Fragment {

    RecyclerView mRecyclerView;

    private MedicinesAdapter medicinesAdapter;
    private RetrofitClient retrofitClient;
    private int pageNumber = 0;
    private MedicinesInterface medicineInterface;
    private MedicineListResponse medicineListResponse;
    private LinearLayout medicineSearch;
    private EditText searchText;
    private Button searchButton;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);

        medicineSearch = (LinearLayout) view.findViewById(R.id.med_search);
        searchText = (EditText) view.findViewById(R.id.search_product_name);
        searchButton = (Button) view.findViewById(R.id.search_btn);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.item_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Long categoryId = (getArguments().getLong("CatID"));
        String clickid = (getArguments().getString("ClickID"));

        if (clickid .equals("Category"))
        {
            medicineSearch.setVisibility(View.GONE);
            renderMedicineList(pageNumber, categoryId, null);
        }

        else
        {
            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String searchInput = searchText.getEditableText().toString();
                    renderMedicineList(pageNumber, null, searchInput );
                }
            });

        }

    return view;
    }

    private void renderMedicineList(int pageNumber, Long categoryId, String medicineName) {


        try {
            retrofitClient = RetrofitClient.getInstance();
            medicineInterface = retrofitClient.getRetrofit().create(MedicinesInterface.class);
            Call<MedicineListResponse> call = medicineInterface.getMedicineList(retrofitClient.getJwtToken(), pageNumber, categoryId, medicineName);

            call.enqueue(new Callback<MedicineListResponse>() {
                @Override
                public void onResponse(Call<MedicineListResponse> call, Response<MedicineListResponse> response) {

                    // loadingBar.dismiss();

                    medicineListResponse = response.body();
                    medicinesAdapter = new MedicinesAdapter(getActivity(), (ArrayList<MedicineResponse>) medicineListResponse.getMedicines());


                    //  categoryAdapter = new CategoryAdapter(data);

                    mRecyclerView.setAdapter(medicinesAdapter);


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



}