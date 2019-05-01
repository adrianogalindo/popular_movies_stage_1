package com.galindo.popular_movies_stage_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class FullPosterImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_poster_image);


        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {

                PhotoView mMoviePosterDisplayContext = findViewById(R.id.fullImageView);
                String poster = getIntent().getStringExtra("poster");
//                imageView.setImageResource(mMoviePosterDisplayContext);

                Picasso.get()
                        .load(poster)
                        .placeholder(R.drawable.loading_icon)
                        .error(R.drawable.not_found_icon)
                        .into(mMoviePosterDisplayContext);


            }
        }
//        Intent i = getIntent();
//
//        int position = i.getExtras().getInt("id");
//        ImageAdapter adapter = new ImageAdapter(this);
//
//        PhotoView imageView = findViewById(R.id.fullImageView);
//        imageView.setImageResource(adapter.images[position]);

    }
}
