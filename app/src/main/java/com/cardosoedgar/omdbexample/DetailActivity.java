package com.cardosoedgar.omdbexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardosoedgar.omdbexample.model.Movie;
import com.squareup.picasso.Picasso;

import java.io.File;

public class DetailActivity extends AppCompatActivity {

    Movie movie;
    Toolbar toolbar;

    ImageView poster;
    TextView title;
    TextView subtitle;
    TextView director;
    TextView country;
    TextView plot;
    TextView actors;
    TextView rating;
    TextView votes;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);
        setToolbar();
        setUI();

        movie = (Movie) getIntent().getSerializableExtra("movie");
        setMovie();
    }

    private void setMovie() {
        Picasso.with(this).load(new File(movie.getPoster())).into(poster);
        String titleString = movie.getTitle() + " (" + movie.getYear() + ")";
        String subtitleString = movie.getRuntime() + ", " + movie.getGenre();
        String countryString = movie.getLanguage() + " (" + movie.getCountry() + ")";
        String ratingString = movie.getRating() + " / " + movie.getMetascore();
        String votesString = movie.getVotes() + " votes";

        title.setText(titleString);
        subtitle.setText(subtitleString);
        director.setText(movie.getDirector());
        country.setText(countryString);
        plot.setText(movie.getPlot());
        actors.setText(movie.getActors());
        rating.setText(ratingString);
        votes.setText(votesString);
    }

    private void setUI() {
        poster = (ImageView) findViewById(R.id.poster);
        title = (TextView) findViewById(R.id.title);
        subtitle = (TextView) findViewById(R.id.subtitle);
        director = (TextView) findViewById(R.id.director);
        country = (TextView) findViewById(R.id.country);
        plot = (TextView) findViewById(R.id.plot);
        actors = (TextView) findViewById(R.id.actors);
        rating = (TextView) findViewById(R.id.rating);
        votes = (TextView) findViewById(R.id.votes);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
