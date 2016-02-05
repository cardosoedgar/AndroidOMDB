package com.cardosoedgar.omdbexample.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by edgarcardoso on 2/4/16.
 */
public class MovieSearch {

    @SerializedName("imdbID") String id;
    @SerializedName("Title") String title;
    @SerializedName("Year") String year;
    @SerializedName("Poster") String poster;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
