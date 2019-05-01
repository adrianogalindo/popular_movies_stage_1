package com.galindo.popular_movies_stage_1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.galindo.popular_movies_stage_1.entities.JsonUtils;
import com.galindo.popular_movies_stage_1.entities.Movie;
import com.galindo.popular_movies_stage_1.entities.NetworkUtils;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler{

    private RecyclerView mMainActivityRecyclerView;
    private MovieAdapter mMovieAdapter;
    private List<Movie> jsonMovieData;

    @BindView(R.id.tv_error_message)
    TextView mErrorMessage;
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    String query = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainActivityRecyclerView = findViewById(R.id.rv_main_activity);

        ButterKnife.bind(this);

        //how many columns can fit in one page. this method explained below.
        int mNoOfColumns = calculateNoOfColumns(getApplicationContext());

        GridLayoutManager layoutManager = new GridLayoutManager(this, mNoOfColumns);
        //set the layout manager
        mMainActivityRecyclerView.setLayoutManager(layoutManager);
        //changes in content shouldn't change the layout size
        mMainActivityRecyclerView.setHasFixedSize(true);

        //set movie adapter for recycler view
        mMainActivityRecyclerView.setAdapter(mMovieAdapter);

        loadMovieData();
    }

    private void loadMovieData() {
        String theMovieDbQueryType = query;
        showJsonDataResults();
        new FetchMovieTask().execute(theMovieDbQueryType);
    }

    @Override
    public void onClick(int adapterPosition) {
        Context context = this;
        Class destinationClass = DetailActivity.class;

        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, adapterPosition);
        intentToStartDetailActivity.putExtra("title", jsonMovieData.get(adapterPosition).getTitle());
        intentToStartDetailActivity.putExtra("poster", jsonMovieData.get(adapterPosition).getPoster());
        intentToStartDetailActivity.putExtra("rate", jsonMovieData.get(adapterPosition).getRating());
        intentToStartDetailActivity.putExtra("release", jsonMovieData.get(adapterPosition).getReleaseDate());
        intentToStartDetailActivity.putExtra("overview", jsonMovieData.get(adapterPosition).getOverview());

        startActivity(intentToStartDetailActivity);
    }

    private void showJsonDataResults() {
        mErrorMessage.setVisibility(View.INVISIBLE);
        mMainActivityRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mMainActivityRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, List<Movie>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movie> doInBackground(String... params) {
            if (params.length == 0){
                return null;
            }

            String sortBy = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(sortBy);

            try {
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
                jsonMovieData = JsonUtils.parseMovieResultsFromJson(MainActivity.this, jsonMovieResponse);
                return jsonMovieData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> movieData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (movieData != null) {
                showJsonDataResults();
                mMovieAdapter = new MovieAdapter(movieData,MainActivity.this);
                mMainActivityRecyclerView.setAdapter(mMovieAdapter);
            } else {
                showErrorMessage();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();

        if (menuItemSelected == R.id.action_popular) {
            query = "popular";
            loadMovieData();
            return true;
        }

        if (menuItemSelected == R.id.action_top_rated) {
            query = "top_rated";
            loadMovieData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //calculates how many columns can I fit in screen.
    //Source: https://stackoverflow.com/questions/33575731/gridlayoutmanager-how-to-auto-fit-columns
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }
}
