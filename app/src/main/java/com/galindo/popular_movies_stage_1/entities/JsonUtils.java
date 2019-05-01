package com.galindo.popular_movies_stage_1.entities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    static final String BASE_URL = "https://image.tmdb.org/t/p/";
    static final String POSTER_SIZE = "w500";
    static final String RESULTS = "results";
    static   final String TITLE = "title";
    static  final String POSTER_PATH = "poster_path";
    static  final String RATING = "vote_average";
    static    final String OVERVIEW = "overview";
    static    final String RELEASE_DATE = "release_date";
    //I've got some help from sunshine app, the sandwichclub app
    //  and https://www.codevoila.com/post/65/java-json-tutorial-and-example-json-java-orgjson#toc_5.

    public static List<Movie> parseMovieResultsFromJson(Context context, String json) throws JSONException {

        JSONObject response = new JSONObject(json);
        List<Movie> movieList = new ArrayList<>();
        JSONArray resultsJsonArray = response.optJSONArray(RESULTS);

        for (int i = 0; i < resultsJsonArray.length(); i++) {
            movieList.add(parseMovie(resultsJsonArray.optString(i)));
        }
        return movieList;

    }

        private static Movie parseMovie(String json) throws JSONException {
            JSONObject movieJson = new JSONObject(json);
            String title = movieJson.optString(TITLE);
            String posterPath = movieJson.optString(POSTER_PATH);
            String rating = movieJson.optString(RATING);
            String releaseDate = movieJson.optString(RELEASE_DATE);
            String overview = movieJson.optString(OVERVIEW);
            String poster = BASE_URL + POSTER_SIZE + posterPath;
            return new Movie(title, poster, rating, releaseDate, overview);
        }
}
