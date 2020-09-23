package com.example.medic.Api_Interfaces;

import com.example.medic.Requests.OrderRequest;
import com.example.medic.Requests.RegisterUserRequest;
import com.example.medic.Responses.OrderResponse;
import com.example.medic.Responses.RegisterUserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OrderInterface {

    @POST("order/placeOrder")
    Call<OrderResponse> placeOrder (@Header("Authorization")String authToken,@Body OrderRequest request);
}
