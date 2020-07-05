package com.example.medic.Api_Interfaces;

import com.example.medic.Responses.CategoryListResponse;
import com.example.medic.Responses.MedicineResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CategoryInterface {

    @GET ("category/list")
    Call<CategoryListResponse> getCategoryList (@Header("Authorization")String authToken);


}
