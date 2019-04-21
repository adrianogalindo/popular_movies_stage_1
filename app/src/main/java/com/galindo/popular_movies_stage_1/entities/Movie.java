package com.galindo.popular_movies_stage_1.entities;

import java.util.ArrayList;

public class Movie {
    private String title;
    private String poster;
    private ArrayList<String> genre = new ArrayList<>();
    private String rating;
    private String releaseDate;
    private String overview;

    public Movie(String title, String poster, String rating, String releaseDate, String overview) {
        this.title = title;
        this.poster = poster;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
       this.genre.add(genre);
    }
}
