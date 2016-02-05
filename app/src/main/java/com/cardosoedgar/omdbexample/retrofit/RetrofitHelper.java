package com.cardosoedgar.omdbexample.retrofit;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by edgarcardoso on 2/4/16.
 */
public class RetrofitHelper {

    Retrofit retrofit;
    public OMDBService service;
    
    public RetrofitHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(OMDBService.class);
    }
}
