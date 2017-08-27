package com.example.mvaguimaraes.musicappcardview.rest;

import com.example.mvaguimaraes.musicappcardview.model.GenreResponse;
import com.example.mvaguimaraes.musicappcardview.model.MovieItem;
import com.example.mvaguimaraes.musicappcardview.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mvaguimaraes on 8/23/17.
 */

public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieItem> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<GenreResponse> getGenres(@Query("api_key") String apiKey);
}