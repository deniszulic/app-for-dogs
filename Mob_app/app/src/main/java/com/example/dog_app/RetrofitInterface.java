package com.example.dog_app;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/register")
    Call<Void> postJson(@Body registerdata body);
    @POST("/login")
    Call<getlogindata[]> login(@Body HashMap<String, String> body);
}
