package com.example.medic.Api_Interfaces;

import com.example.medic.Responses.MedicineListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MedicinesInterface {

    @GET("medicine/search")
    Call<MedicineListResponse> getMedicineList(@Header("Authorization") String authToken,
                                               @Query("pageNumber") int pageNumber,
                                               @Query("categoryId") Long categoryId,
                                               @Query("medicineName") String medicineName
                                               );
}
