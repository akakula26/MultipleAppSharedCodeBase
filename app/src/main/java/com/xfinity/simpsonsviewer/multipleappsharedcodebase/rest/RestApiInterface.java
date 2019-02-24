package com.xfinity.simpsonsviewer.multipleappsharedcodebase.rest;

import com.xfinity.simpsonsviewer.multipleappsharedcodebase.model.ModelClass;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface RestApiInterface {

    @GET("/")
    Call<ModelClass> dataSearch(@Query(value = "q",encoded = true) String list, @QueryMap HashMap<String, String> request);
}
