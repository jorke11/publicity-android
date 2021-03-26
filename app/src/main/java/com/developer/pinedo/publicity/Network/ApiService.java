package com.developer.pinedo.publicity.Network;

import com.developer.pinedo.publicity.Entities.AccessToken;
import com.developer.pinedo.publicity.Entities.Publicity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login")
    @FormUrlEncoded
    Call<AccessToken> login(@Field("email") String email,@Field("password") String password);

    @GET("publicity")
    Call<List<Publicity>> listPublicity();

    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);
}
