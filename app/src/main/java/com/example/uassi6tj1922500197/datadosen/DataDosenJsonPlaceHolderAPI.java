package com.example.uassi6tj1922500197.datadosen;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface DataDosenJsonPlaceHolderAPI {
    @GET("datadosen.php")
    Call<List<PostDosen>> getPosts(

    );
    @GET("datadosen.php")
    Call<List<PostDosen>> getPosts(@QueryMap Map<String, String> parameters);
}
