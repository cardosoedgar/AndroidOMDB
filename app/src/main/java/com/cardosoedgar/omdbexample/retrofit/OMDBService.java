package com.cardosoedgar.omdbexample.retrofit;

import com.cardosoedgar.omdbexample.model.Movie;
import com.cardosoedgar.omdbexample.model.Search;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by edgarcardoso on 2/4/16.
 */
public interface OMDBService {

    @GET("http://www.omdbapi.com")
    Call<Search> getMovies(@Query("s") String movieName, @Query("type") String type, @Query("page") int page);

    @GET("/")
    Call<Movie> getMovieWithId(@Query("i") String id);
}
