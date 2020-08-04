package com.example.medic.Api_Interfaces;

import com.example.medic.Requests.AddToCartRequest;
import com.example.medic.Responses.AddToCartResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AddToCartInterface {

    @POST("cart/add")
    Call<AddToCartResponse> addToCart (@Header("Authorization")String authToken, @Body AddToCartRequest request);
}
