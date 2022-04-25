package com.example.dog_app;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {
    @GET("/getmissingdogs")
    Call<ListItem[]> missingdogs();
    @GET("/getmissingdogsshelter")
    Call<Missingdogs_user_shelter_data[]> missingdogs_usershelter();
    @GET("/getmissingdogs/{id}")
    Call<ListItem[]> getcommentsuser(@Path("id") int id);
    @GET("/getspecificdogshelter/{id}")
    Call<Missingdogs_user_shelter_data[]> getdatashelter(@Path("id") int id);
    @GET("/getcomments/{id}")
    Call<Comments[]> getcomments(@Path("id") int id);
    @POST("/register")
    Call<Void> postJson(@Body registerdata body);
    @POST("/login")
    Call<getlogindata[]> login(@Body HashMap<String, String> body);
    @POST("/register")
    Call<Integer> register(@Body registerdata body);
    @POST("/registerasylum")
    Call<Integer> shelterregister(@Body registershelter body);
    @POST("/getcommentsfordog")
    Call<Void> sendcomment(@Body Sendcomment body);
    @POST("/dogmissing")
    Call<Void> missingdog(@Body missingdogsdata body);
    @POST("/reportmissingdog")
    Call<Void> reportmissingdog(@Body Reportmissingdog body);
    @DELETE("delete/{id}")
    Call<Void> deleteuser(@Path("id") int id);
    @DELETE("deletecomment/{id}")
    Call<Void> deletecomment(@Path("id") int id);
}
