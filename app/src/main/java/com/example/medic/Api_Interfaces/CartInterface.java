package com.example.medic.Api_Interfaces;

import com.example.medic.Requests.AddToCartRequest;
import com.example.medic.Responses.AddToCartResponse;
import com.example.medic.Responses.CartDetailResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CartInterface {

    @POST("cart/add")
    Call<AddToCartResponse> addToCart (@Header("Authorization")String authToken, @Body AddToCartRequest request);

    @GET("cart/getCart")
    Call<CartDetailResponse> getCart (@Header("Authorization")String authToken);
}
