package com.xfinity.simpsonsviewer.multipleappsharedcodebase.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {


    public static final String BASE_URL = "http://api.duckduckgo.com";

    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){

        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
