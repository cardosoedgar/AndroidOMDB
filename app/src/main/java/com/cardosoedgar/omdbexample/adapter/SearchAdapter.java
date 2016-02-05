package com.cardosoedgar.omdbexample.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cardosoedgar.omdbexample.R;
import com.cardosoedgar.omdbexample.model.MovieSearch;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by edgarcardoso on 2/4/16.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context context;
    List<MovieSearch> movieList;

    public SearchAdapter(Context context, List<MovieSearch> movieList) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_search_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieSearch movie = movieList.get(position);
        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());
        holder.id = movie.getId();

        Picasso.with(context).load(movie.getPoster()).into(holder.poster);

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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        String id;
        TextView title;
        TextView year;
        ImageView poster;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.title);
            year = (TextView) itemView.findViewById(R.id.year);
            poster = (ImageView) itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View view) {
            Activity activity = (Activity) view.getContext();
            Intent intent = new Intent();
            intent.putExtra("movieId", id);
            activity.setResult(Activity.RESULT_OK, intent);
            activity.finish();
        }
    }
}
