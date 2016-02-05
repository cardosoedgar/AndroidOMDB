package com.cardosoedgar.omdbexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardosoedgar.omdbexample.R;
import com.cardosoedgar.omdbexample.model.Movie;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by edgarcardoso on 2/4/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        String title = movie.getTitle() + " (" + movie.getYear() + ")";
        String subtitle = movie.getRuntime() + ", " + movie.getGenre();

        holder.title.setText(title);
        holder.subtitle.setText(subtitle);
        holder.rating.setText(movie.getRating());

        File imageFile = new File(movie.getPoster());
        Picasso.with(context).load(imageFile).into(holder.poster);
        setBackgroundColor(holder.itemView, position);
    }

    private void setBackgroundColor(View view, int position) {
        if(position % 2 == 0)
            view.setBackgroundResource(R.color.colorRowGray);
        else
            view.setBackgroundResource(R.color.colorRowWhite);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subtitle;
        TextView rating;
        ImageView poster;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            rating = (TextView) itemView.findViewById(R.id.rating);
            poster = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
