package com.example.dog_app;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
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
    @GET("/getadopteddogs")
    Call<useradopteddogdata[]> getuseradopteddog();
    @GET("/getadopteddogsshelter")
    Call<shelteradopteddogdata[]> getshelteradopteddogs();
    @GET("/changeuserdata/{email}")
    Call<profildata[]> getdataprofile(@Path("email") String email);
    @GET("/getmymissingdogs/{email}")
    Call<mymissingdogsdata[]> getmymissingdogs(@Path("email") String email);
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
    @POST("/adoptdog")
    Call<Void> adoptdog(@Body adopteddogdata body);
    @POST("/adoptdogapplication")
    Call<Void> reportadopdog(@Body adopt_dog_application body);
    @DELETE("delete/{id}")
    Call<Void> deleteuser(@Path("id") int id);
    @DELETE("deletecomment/{id}")
    Call<Void> deletecomment(@Path("id") int id);
    @PATCH("/updatemydata/{id}")
    Call<profildata[]> updatedata(@Path("id") int id, @Body profildata change);
    @PATCH("/updatepass/{id}")
    Call<passworddata[]> updatepassword(@Path("id") int id, @Body passworddata change);
    @PATCH("/updatemissingdog/{id}")
    Call<updatemymissingdog[]> updatemymissingdog(@Path("id") int id, @Body updatemymissingdog change);
}
