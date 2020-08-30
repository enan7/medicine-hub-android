package com.example.medic.Api_Interfaces;

import com.example.medic.Requests.AddToCartRequest;
import com.example.medic.Responses.AddToCartResponse;
import com.example.medic.Responses.CartDeleteResponse;
import com.example.medic.Responses.CartDetailResponse;
import com.example.medic.Responses.CartItemDeleteResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CartInterface {

    @POST("cart/add")
    Call<AddToCartResponse> addToCart (@Header("Authorization")String authToken, @Body AddToCartRequest request);

    @GET("cart/getCart")
    Call<CartDetailResponse> getCart (@Header("Authorization")String authToken);

    @DELETE("cart/delete")
    Call<CartDeleteResponse> deleteCart (@Header("Authorization")String authToken);

    @DELETE("cart/deleteItem/")
    Call<CartItemDeleteResponse> deleteCartItem (@Header("Authorization")String authToken,  @Query("itemId") Long itemId);


}
