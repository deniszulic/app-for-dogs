package com.example.dog_app;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @POST("/register")
    Call<Void> postJson(@Body registerdata body);
    @POST("/login")
    Call<getlogindata[]> login(@Body HashMap<String, String> body);
    @POST("/register")
    Call<Integer> register(@Body registerdata body);
    @POST("/registerasylum")
    Call<Integer> shelterregister(@Body registershelter body);
    @POST("/dogmissing")
    Call<Void> missingdog(@Body missingdogsdata body);
    @DELETE("delete/{id}")
    Call<Void> deleteuser(@Path("id") int id);
}
