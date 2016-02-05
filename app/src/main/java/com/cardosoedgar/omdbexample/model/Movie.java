package com.cardosoedgar.omdbexample.model;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by edgarcardoso on 2/4/16.
 */
public class Movie extends SugarRecord implements Serializable{

    @SerializedName("imdbID") String movieId;
    @SerializedName("Title") String title;
    @SerializedName("Year") String year;
    @SerializedName("Poster") String poster;
    @SerializedName("Runtime") String runtime;
    @SerializedName("Genre") String genre;
    @SerializedName("Director") String director;
    @SerializedName("Writer") String writer;
    @SerializedName("Actors") String actors;
    @SerializedName("Plot") String plot;
    @SerializedName("Language") String language;
    @SerializedName("Country") String country;
    @SerializedName("Awards") String awards;
    @SerializedName("Metascore") String metascore;
    @SerializedName("imdbRating") String rating;
    @SerializedName("imdbVotes") String votes;

    public Movie() {

    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
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

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
}
