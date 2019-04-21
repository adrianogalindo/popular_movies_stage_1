package com.galindo.popular_movies_stage_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {



    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movies_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View layout = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieAdapterViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder movieAdapterViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MovieAdapterViewHolder extends RecyclerView.ViewHolder{

        private ImageView mMovieImage;
        private TextView mMovieTitle;
        private TextView mMovieGenre;

        public MovieAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            mMovieImage = itemView.findViewById(R.id.iv_movie_image);
            mMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            mMovieGenre = itemView.findViewById(R.id.tv_movie_genre);


        }
    }
}
