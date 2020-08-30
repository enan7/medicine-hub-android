package com.example.medic.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.medic.Activity.Home;
import com.example.medic.Adapters.CategoryAdapter;
import com.example.medic.Api_Interfaces.CategoryInterface;
import com.example.medic.R;
import com.example.medic.Responses.CategoryListResponse;
import com.example.medic.Responses.CategoryDTO;
import com.example.medic.RetrofitClient.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {

    private LinearLayout searchButton;
    private RetrofitClient retrofitClient;
    private CategoryListResponse categoryListResponse;
    private CategoryAdapter categoryAdapter;
    RecyclerView categoryRecyclerView;
    RelativeLayout progressBar;
    private CategoryInterface categoryInterface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        searchButton = (LinearLayout) view.findViewById(R.id.search);
        categoryRecyclerView = (RecyclerView) view.findViewById(R.id.cat_recyclerview);
        progressBar = (RelativeLayout) view.findViewById(R.id.progressbar);


        initViews();


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle = new Bundle();
                bundle.putString("ClickID", "Search");
                MedicineFragment medicineFragment = new MedicineFragment();
                medicineFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, medicineFragment);
                transaction.addToBackStack(null).commit();


            }
        });


        return view;
    }

    private void initViews() {

        categoryRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        getCategoryList();
    }

    private void getCategoryList() {

        try {
            retrofitClient = RetrofitClient.getInstance();
            categoryInterface = retrofitClient.getRetrofit().create(CategoryInterface.class);
            Call<CategoryListResponse> call = categoryInterface.getCategoryList(retrofitClient.getJwtToken());

            call.enqueue(new Callback<CategoryListResponse>() {
                @Override
                public void onResponse(Call<CategoryListResponse> call, Response<CategoryListResponse> response) {


                    categoryListResponse = response.body();
                    categoryAdapter = new CategoryAdapter(getActivity(), (ArrayList<CategoryDTO>) categoryListResponse.getCategories());


                    categoryRecyclerView.setAdapter(categoryAdapter);


                    //   Toast.makeText(SignUp.this,registerUserResponse.getResponseMessage(),Toast.LENGTH_LONG).show();

                    /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                     *//*toolbar.setLogo(R.drawable.icon);*//*
                    setSupportActionBar(toolbar);
                    getSupportActionBar().setTitle(null);*/

/*
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                            Home.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                    drawer.addDrawerListener(toggle);
                    toggle.syncState();

                    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                    navigationView.setNavigationItemSelectedListener(Home.this);*/
                    progressBar.setVisibility(View.GONE);
                    categoryAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<CategoryListResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
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
            activity.showDrawerButton();
            activity.hideBackButton();
        }

    }


}



