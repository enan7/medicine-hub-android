package com.example.medic.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medic.Adapters.OrderDetailAdapter;
import com.example.medic.Api_Interfaces.OrderInterface;
import com.example.medic.R;
import com.example.medic.Responses.OrderDetailsResponse;
import com.example.medic.Responses.OrderedItemsDto;
import com.example.medic.RetrofitClient.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderDetailFragment extends Fragment {


    private TextView refNumber, receiverName, receiverAddress, receiverTelephone, receiverNearby ;
    RecyclerView mRecyclerView;
    ProgressBar progressBar;
    private OrderInterface orderInterface;
    private RetrofitClient retrofitClient;
    private OrderDetailAdapter orderDetailAdapter;
    private OrderDetailsResponse orderDetailsResponse;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);

        long orderId = (Long) getArguments().getLong("OrderId");

        refNumber = view.findViewById(R.id.ref_number);
        receiverTelephone = view.findViewById(R.id.rec_telnumber);
        receiverName = view.findViewById(R.id.rec_name);
        receiverAddress = view.findViewById(R.id.rec_address);
        receiverNearby = view.findViewById(R.id.rec_nearby);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.order_detail_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        orderDetail(orderId);


        return view;
    }

    private void orderDetail(Long OrderId) {

        try {
            retrofitClient = RetrofitClient.getInstance();
            orderInterface = retrofitClient.getRetrofit().create(OrderInterface.class);
            Call<OrderDetailsResponse> call = orderInterface.getOrdersDetail(retrofitClient.getJwtToken(), OrderId);

            call.enqueue(new Callback<OrderDetailsResponse>() {
                @Override
                public void onResponse(Call<OrderDetailsResponse> call, Response<OrderDetailsResponse> response) {

                    // loadingBar.dismiss();

                    orderDetailsResponse = response.body();

                    orderDetailAdapter = new OrderDetailAdapter(getActivity(), (ArrayList<OrderedItemsDto>) orderDetailsResponse.getOrderedItems());


                    //  categoryAdapter = new CategoryAdapter(data);

                    refNumber.setText(orderDetailsResponse.getOrder().getReferenceNumber());
                    receiverName.setText(orderDetailsResponse.getOrderAddress().getReceiverName());
                    receiverTelephone.setText(orderDetailsResponse.getOrderAddress().getPhoneNumber());
                    receiverAddress.setText(orderDetailsResponse.getOrderAddress().getHouseNumber());
                    receiverNearby.setText(orderDetailsResponse.getOrderAddress().getNearByLocation());

                    mRecyclerView.setAdapter(orderDetailAdapter);

                    progressBar.setVisibility(View.GONE);


                    //   Toast.makeText(SignUp.this,registerUserResponse.getResponseMessage(),Toast.LENGTH_LONG).show();


                }

                @Override
                public void onFailure(Call<OrderDetailsResponse> call, Throwable t) {
                    // loadingBar.dismiss();
                    System.out.println("Failed");
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}