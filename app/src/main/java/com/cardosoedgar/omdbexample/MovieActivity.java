package com.cardosoedgar.omdbexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.cardosoedgar.omdbexample.adapter.MovieAdapter;
import com.cardosoedgar.omdbexample.model.Movie;
import com.cardosoedgar.omdbexample.retrofit.RetrofitHelper;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {

    private static final int RESULT_MOVIE_ID = 1;
    FloatingActionButton fab;
    Toolbar toolbar;
    ProgressBar progressBar;

    //RECYCLER VIEW
    RecyclerView recyclerView;
    MovieAdapter adapter;
    LinearLayoutManager layoutManager;

    List<Movie> movieList;

    Target target;
    ImageHelper imageHelper;
    RetrofitHelper retrofitHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieList = getMoviesFromDB();

        imageHelper = new ImageHelper(this);
        retrofitHelper = new RetrofitHelper();

        setToolbar();
        setRecyclerView();
        setFab();
        setProgressBar();
    }

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new MovieAdapter(this, movieList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setProgressBar() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        hideProgressIndicator();
    }

    private List<Movie> getMoviesFromDB() {
        return Movie.listAll(Movie.class);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setFab() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSearchActivity();
            }
        });
    }

    private void startSearchActivity() {
        Intent intent = new Intent(this,SearchMovieActivity.class);
        startActivityForResult(intent, RESULT_MOVIE_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_MOVIE_ID && resultCode == RESULT_OK) {
            getMovie(data.getStringExtra("movieId"));
        }
    }

    private void getMovie(String movieId) {
        showProgressIndicator();
        retrofitHelper.service.getMovieWithId(movieId).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                downloadPoster(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                showSnackBar();
            }
        });
    }

    private void downloadPoster(Movie movie) {
        target = createTarget(movie);
        Picasso.with(this).load(movie.getPoster()).into(target);
    }

    private Target createTarget(final Movie movie) {
        return new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                String imagePath = imageHelper.saveImage(bitmap,movie.getMovieId()+".png");
                saveMovie(movie,imagePath);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                saveMovie(movie, "");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
    }

    private void saveMovie(Movie movie, String imagePath) {
        movie.setPoster(imagePath);
        movie.save();
        movieList.add(movie);
        adapter.notifyDataSetChanged();
        hideProgressIndicator();
    }

    public void hideProgressIndicator() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void showProgressIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void showSnackBar() {
        Snackbar.make(findViewById(R.id.view),
                "Não foi possível conectar com o servidor.", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        Picasso.with(this).cancelRequest(target);
        super.onDestroy();
    }
}
