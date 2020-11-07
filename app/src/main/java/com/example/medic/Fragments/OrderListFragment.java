package com.example.medic.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.order_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        renderOrderList(pageNumber);



        return view;

    }

    private void renderOrderList(int pageNumber) {


        try {
            retrofitClient = RetrofitClient.getInstance();
            orderInterface = retrofitClient.getRetrofit().create(OrderInterface.class);
            Call<GetOrderByUserResponse> call = orderInterface.getOrdersByUser(retrofitClient.getJwtToken(), pageNumber);

            call.enqueue(new Callback<GetOrderByUserResponse>() {
                @Override
                public void onResponse(Call<GetOrderByUserResponse> call, Response<GetOrderByUserResponse> response) {

                    // loadingBar.dismiss();

                    getOrderByUserResponse = response.body();
                    statusAdapter = new OrderListAdapter(getActivity(), (ArrayList<OrdersDto>) getOrderByUserResponse.getOrders());


                    //  categoryAdapter = new CategoryAdapter(data);

                    mRecyclerView.setAdapter(statusAdapter);
                    progressBar.setVisibility(View.GONE);


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