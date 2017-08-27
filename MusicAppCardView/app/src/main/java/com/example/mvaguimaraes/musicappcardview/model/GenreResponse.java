package com.example.mvaguimaraes.musicappcardview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mvaguimaraes on 8/25/17.
 */

public class GenreResponse {

    @SerializedName("genres")
    private List<MovieGenre> results;

    public List<MovieGenre> getResults() {
        return results;
    }

    public void setResults(List<MovieGenre> results) {
        this.results = results;
    }
}
