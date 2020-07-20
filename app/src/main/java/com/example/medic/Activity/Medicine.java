package com.example.medic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

public class Medicine extends ToolBar {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_medicine);


        medicineSearch = findViewById(R.id.med_search);
        searchText = findViewById(R.id.search_product_name);
        searchButton = findViewById(R.id.search_btn);



        Long categoryId = (getIntent().getExtras().getLong("CatID"));
        String clickid = (getIntent().getExtras().getString("ClickID"));

      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
*/
        mRecyclerView = findViewById(R.id.item_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



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
                    medicinesAdapter = new MedicinesAdapter(Medicine.this, (ArrayList<MedicineResponse>) medicineListResponse.getMedicines());


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.cart, menu);

        MenuItem mCartIconMenuItem = menu.findItem(R.id.cart_count_menu_item);
        View actionView = mCartIconMenuItem.getActionView();
        /*if (actionView!=null)
        *//*{
            CartImageBtn = actionView.findViewById(R.id.cart_image_button);
            CartCountTv = actionView.findViewById(R.id.count_tv_layout);
        }


        //Cart toolbar image view
        CartImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }*//*
        });*/

        return super.onCreateOptionsMenu(menu);
    }


}
