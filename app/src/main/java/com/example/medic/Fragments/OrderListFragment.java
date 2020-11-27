package com.example.medic.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Activity.Home;
import com.example.medic.Adapters.OrderListAdapter;
import com.example.medic.Api_Interfaces.OrderInterface;
import com.example.medic.R;
import com.example.medic.Responses.GetOrderByUserResponse;
import com.example.medic.Responses.OrdersDto;
import com.example.medic.RetrofitClient.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class OrderListFragment extends Fragment {

    RecyclerView mRecyclerView;
    ProgressBar progressBar;

    private OrderListAdapter statusAdapter;
    private RetrofitClient retrofitClient;
    private int pageNumber = 0;
    private OrderInterface orderInterface;
    private GetOrderByUserResponse getOrderByUserResponse;
    private ArrayList<OrdersDto> orderList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private int scrollState;
    private int scrollOnY;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);


        statusAdapter=null;
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.order_recyclerview);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

              //  super.onScrollStateChanged(recyclerView, newState);
                scrollState = newState;
                if(scrollState == 0) {
                    if(scrollOnY>0) {
//
                    pageNumber = ++pageNumber;
                    renderOrderList(pageNumber);
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
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

       renderOrderList(0);


        return view;

    }



    private void renderOrderList(final int pageNumber) {

        progressBar.setVisibility(View.VISIBLE);
        try {
            retrofitClient = RetrofitClient.getInstance();
            orderInterface = retrofitClient.getRetrofit().create(OrderInterface.class);
            Call<GetOrderByUserResponse> call = orderInterface.getOrdersByUser(retrofitClient.getJwtToken(), pageNumber);

            call.enqueue(new Callback<GetOrderByUserResponse>() {
                @Override
                public void onResponse(Call<GetOrderByUserResponse> call, Response<GetOrderByUserResponse> response) {

                    // loadingBar.dismiss();
                    progressBar.setVisibility(View.GONE);

                    getOrderByUserResponse = response.body();
                    if(null!=getOrderByUserResponse && null!=getOrderByUserResponse.getOrders()) {
                        if(getOrderByUserResponse.getOrders().size()!=0)
                        orderList.addAll(getOrderByUserResponse.getOrders());
                        if(null == statusAdapter) {
                            statusAdapter = new OrderListAdapter(getActivity(), orderList);
                            mRecyclerView.setAdapter(statusAdapter);

                        } else{
                            statusAdapter.getrOrderList().addAll(getOrderByUserResponse.getOrders());
                            statusAdapter.notifyItemInserted(statusAdapter.getrOrderList().size()+1);
                            //mRecyclerView.refreshDrawableState();
                        }
                    }

                    //  categoryAdapter = new CategoryAdapter(data);



                    //   Toast.makeText(SignUp.this,registerUserResponse.getResponseMessage(),Toast.LENGTH_LONG).show();


                }

                @Override
                public void onFailure(Call<GetOrderByUserResponse> call, Throwable t) {
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