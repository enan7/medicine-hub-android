package com.example.medic.RetrofitClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://medicine-hub-api.herokuapp.com/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    private  Object response = null;
    private String jwtToken;

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = "Bearer "+ jwtToken;
    }

    private RetrofitClient()
    {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance()

    {
        if (mInstance == null) {

            mInstance = new RetrofitClient();
        }

        return mInstance;
    }





}
