package com.example.medic.Api_Interfaces;

import com.example.medic.Requests.LoginUserRequest;
import com.example.medic.Requests.RegisterUserRequest;
import com.example.medic.Responses.LoginUserResponse;
import com.example.medic.Responses.RegisterUserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserInterface {
    @POST("auth/register")
    Call<RegisterUserResponse> registerUser(@Body RegisterUserRequest request);
    @POST("auth/login")
    Call<LoginUserResponse> loginUser(@Body LoginUserRequest request);
}
