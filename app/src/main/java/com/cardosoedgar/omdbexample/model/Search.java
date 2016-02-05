package com.cardosoedgar.omdbexample.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgarcardoso on 2/4/16.
 */
public class Search {
    @SerializedName("Search") List<MovieSearch> movieList;
    @SerializedName("totalResults") int totalResults;

    public Search() {
        movieList = new ArrayList<>();
    }

    public List<MovieSearch> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieSearch> movieList) {
        this.movieList = movieList;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
