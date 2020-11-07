package com.example.medic.Api_Interfaces;

import com.example.medic.Requests.OrderRequest;
import com.example.medic.Requests.RegisterUserRequest;
import com.example.medic.Responses.GetOrderByUserResponse;
import com.example.medic.Responses.OrderResponse;
import com.example.medic.Responses.RegisterUserResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderInterface {

    @POST("order/placeOrder")
    Call<OrderResponse> placeOrder (@Header("Authorization")String authToken,@Body OrderRequest request);

    @GET("order/getOrdersByUser")
    Call<GetOrderByUserResponse> getOrdersByUser (@Header("Authorization")String authToken, @Query("pageNumber") int pageNumber);


}
