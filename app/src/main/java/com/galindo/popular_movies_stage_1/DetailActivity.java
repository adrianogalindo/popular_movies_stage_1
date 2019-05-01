package com.galindo.popular_movies_stage_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                String movie = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                populateUI(movie);

                ImageView poster = findViewById(R.id.iv_detail_movie_poster);
                poster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context =getApplicationContext();
                        Class destinationClass = FullPosterImage.class;
                        Intent fullImageIntent = new Intent(context, destinationClass);
                        fullImageIntent.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra("poster"));
                        fullImageIntent.putExtra("poster", getIntent().getStringExtra("poster"));
                        startActivity(fullImageIntent);
                    }
                });
            }
        } else {
            closeOnError();
        }


    }

    private void populateUI(String movie) {

        // https://stackoverflow.com/questions/41791737/how-to-pass-json-image-from-recycler-view-to-another-activity
        String poster = getIntent().getStringExtra("poster");
        String title = getIntent().getStringExtra("title");
        String rating = getIntent().getStringExtra("rate");
        String releaseDate = getIntent().getStringExtra("release");
        String overview = getIntent().getStringExtra("overview");

        ImageView mMoviePosterDisplayContext = findViewById(R.id.iv_detail_movie_poster);
        Picasso.get()
                .load(poster)
                .placeholder(R.drawable.loading_icon)
                .error(R.drawable.not_found_icon)
                .into(mMoviePosterDisplayContext);
        TextView detailTitleView = findViewById(R.id.tv_detail_title);
        detailTitleView.setText(title);
        TextView detailRatingView = findViewById(R.id.tv_detail_rating);
        detailRatingView.setText(rating);
        TextView releaseDateView = findViewById(R.id.tv_detail_release_date);
        releaseDateView.setText(releaseDate);
        TextView detailOverviewView = findViewById(R.id.tv_plot_synopsis);
        detailOverviewView.setText(overview);

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

}
