package com.cardosoedgar.omdbexample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import com.cardosoedgar.omdbexample.adapter.SearchAdapter;
import com.cardosoedgar.omdbexample.model.MovieSearch;
import com.cardosoedgar.omdbexample.model.Search;
import com.cardosoedgar.omdbexample.retrofit.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovieActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RetrofitHelper retrofitHelper;
    List<MovieSearch> movieList;
    int totalResults;
    int currentPage;
    String query;

    ProgressBar progressBar;
    Toolbar toolbar;
    SearchView searchView;

    //RECYCLER VIEW
    RecyclerView recyclerView;
    SearchAdapter adapter;
    LinearLayoutManager layoutManager;
    boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        movieList = new ArrayList<>();
        retrofitHelper = new RetrofitHelper();
        setToolbar();
        setRecyclerView();
        setProgressBar();
    }

    private void setProgressBar() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        hideProgressIndicator();
    }

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new SearchAdapter(this, movieList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            requestMovies();
                        }
                    }
                }
            }
        });
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setSearchView(Menu menu) {
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView.setOnQueryTextListener(this);
        searchView.setIconified(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        setSearchView(menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!query.isEmpty()) {
            searchView.clearFocus();
            makeNewRequest(query);
        }
        return false;
    }

    private void makeNewRequest(String query) {
        movieList.clear();
        this.query = query;
        currentPage = 1;
        requestMovies();
    }

    public void requestMovies() {
        if(canRequestNewPage() || currentPage == 1) {
            getMoviesWithPagination();
        }
    }

    public void getMoviesWithPagination() {
        showProgressIndicator();
        retrofitHelper.service.getMovies(query, "movie", currentPage).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                totalResults = response.body().getTotalResults();
                movieList.addAll(response.body().getMovieList());

                adapter.notifyDataSetChanged();
                hideProgressIndicator();
                loading = true;
                currentPage++;
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                showSnackBar();
                hideProgressIndicator();
            }
        });
    }

    private void showSnackBar() {
        Snackbar.make(findViewById(R.id.view),
                "Não foi possível conectar com o servidor.", Snackbar.LENGTH_SHORT).show();
    }

    public void hideProgressIndicator() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void showProgressIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public boolean canRequestNewPage() {
        return (currentPage-1) * 10 < totalResults;
    }
}