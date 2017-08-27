package com.example.mvaguimaraes.musicappcardview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvaguimaraes.musicappcardview.model.MovieGenre;
import com.example.mvaguimaraes.musicappcardview.model.MovieItem;
import com.example.mvaguimaraes.musicappcardview.rest.ApiClient;
import com.example.mvaguimaraes.musicappcardview.rest.ApiInterface;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mvaguimaraes on 8/23/17.
 */

public class MovieDetails extends AppCompatActivity {

    private ImageView movie_cover, star1, star2, star3, star4, star5;
    private TextView movie_name, movie_description, movie_rating, movie_date, movie_language,genres_names, duration;
    private Bitmap image;
    private Handler handler;
    private String backdrop,url, runtime, language;
    private Palette palette;
    private static final String TAG = "MovieDetails";
    private ApiInterface apiService;
    private final static String API_KEY = "ff75e7ade3bd186765380cb5bb77eff9";
    private List<MovieGenre> genres;
    private List<MovieItem> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        movie_cover = (ImageView) findViewById(R.id.movie_cover);
        movie_name = (TextView) findViewById(R.id.movie_name);
        movie_description = (TextView) findViewById(R.id.movie_description);
        movie_rating = (TextView) findViewById(R.id.movie_rating);
        movie_date = (TextView) findViewById(R.id.movie_date);
        movie_language = (TextView) findViewById(R.id.movie_language);
        genres_names = (TextView) findViewById(R.id.genres);
        duration = (TextView) findViewById(R.id.duration);
        star1 = (ImageView) findViewById(R.id.rating_image1);
        star2 = (ImageView) findViewById(R.id.rating_image2);
        star3 = (ImageView) findViewById(R.id.rating_image3);
        star4 = (ImageView) findViewById(R.id.rating_image4);
        star5 = (ImageView) findViewById(R.id.rating_image5);

        String name = getIntent().getStringExtra("movie_name");
        String rating = getIntent().getStringExtra("movie_rating");
        String description = getIntent().getStringExtra("movie_description");
        String cover = getIntent().getStringExtra("movie_cover");
        String date = getIntent().getStringExtra("movie_date");
        language = getIntent().getStringExtra("movie_language");
        backdrop = getIntent().getStringExtra("movie_backdrop");
        String id = getIntent().getStringExtra("id");
        System.out.println("Details: " + id);

        //image = MyAsync.execute();

        apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieItem> call = apiService.getMovieDetails(Integer.parseInt(id),API_KEY);
        call.enqueue(new Callback<MovieItem>() {
            @Override
            public void onResponse(Call<MovieItem> call, Response<MovieItem> response) {
                int statusCode = response.code();

                genres = new ArrayList<>();
                genres = response.body().getListGenres();
                System.out.println(genres.get(0).getName());
                String genre = "<b>Genre(s): </b>";

                for (int i = 0; i< genres.size(); i++){

                    if (i == 0) {
                        genre = genre.concat(genres.get(i).getName());
                    } else {
                        genre = genre.concat(", " + genres.get(i).getName());
                    }

                }

                genres_names.setText(Html.fromHtml(genre));
                duration.setText(Html.fromHtml(" <b>Duration: </b>" + response.body().getRuntime() + " minutes"));
                //movie_language.setText("<b>"+ language.toUpperCase() + " - " + response.body().getRuntime() + " minutes</b>");
            }

            @Override
            public void onFailure(Call<MovieItem> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        @SuppressLint("StaticFieldLeak") MyAsync obj = new MyAsync(){

            @Override
            protected void onPostExecute(Bitmap bmp) {
                super.onPostExecute(bmp);

                Bitmap bm = bmp;

                if (bm != null && !bm.isRecycled()) {
                    Palette palette = Palette.from(bm).generate();
                    //System.out.println(palette.getVibrantSwatch().toString().substring(16, Math.min(palette.getVibrantSwatch().toString().length(), 22)));
                    LinearLayout lLayout = (LinearLayout) findViewById(R.id.layout_bg);

                    if(palette.getVibrantSwatch() != null) {
                        lLayout.setBackgroundColor(Color.parseColor("#" + palette.getVibrantSwatch().toString().substring(16, Math.min(palette.getVibrantSwatch().toString().length(), 22))));
                    } else {
                        int randomNum = 0 + (int)(Math.random() * 4);
                        if (randomNum == 0) {
                            //Pink
                            lLayout.setBackgroundColor(Color.parseColor("#f48f97")); //#FFB3BA
                        } else if (randomNum == 1) {
                            //Blue
                            lLayout.setBackgroundColor(Color.parseColor("#25a2a6")); //#BAE1FF
                        } else if (randomNum == 2) {
                            //Green
                            lLayout.setBackgroundColor(Color.parseColor("#a4d05f")); //#BCEDB5
                        } else if (randomNum == 3) {
                            //Orange
                            lLayout.setBackgroundColor(Color.parseColor("#fddd62")); //#ffcf99
                        }
                    }
                }


            }
        };

        obj.execute();

        String[] parts = date.split("-");
        String year = parts[0];
        String month = parts[1];
        String day = parts[2];

        String bold1 = "<b>" + name + "</b>" + " (" + year + ")";
        String bold2 = "<b>Release date: </b>" + day + "/" + month + "/" + year;
        String bold3 = "<b>Original language: </b>" + language.toUpperCase();
        //String bold3 = "<b>"+ language.toUpperCase() + "</b>";
        String bold4 = "<b>Rating: </b>" + rating + "/10";

        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500" + backdrop).into(movie_cover);
        movie_name.setText(Html.fromHtml(bold1));
        movie_description.setText(description);
        movie_date.setText(Html.fromHtml(bold2));
        movie_language.setText(Html.fromHtml(bold3));
        movie_rating.setText(Html.fromHtml(bold4));

        Double rating_double = Double.parseDouble(rating);

        System.out.println(rating + "\n" + (int) Math.round(rating_double));

        if ((int) Math.round(rating_double) == 1){

            star1.setBackgroundResource(R.drawable.half_star_border);
            star1.setVisibility(View.VISIBLE);
            star2.setBackgroundResource(R.drawable.star_empty);
            star3.setBackgroundResource(R.drawable.star_empty);
            star4.setBackgroundResource(R.drawable.star_empty);
            star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(rating_double) == 2){

            star1.setBackgroundResource(R.drawable.full_star);
            star2.setBackgroundResource(R.drawable.star_empty);
            star3.setBackgroundResource(R.drawable.star_empty);
            star4.setBackgroundResource(R.drawable.star_empty);
            star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(rating_double) == 3){

            star1.setBackgroundResource(R.drawable.full_star);
            star2.setBackgroundResource(R.drawable.half_star_border);
            star2.setVisibility(View.VISIBLE);
            star3.setBackgroundResource(R.drawable.star_empty);
            star4.setBackgroundResource(R.drawable.star_empty);
            star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(rating_double) == 4){

            star1.setBackgroundResource(R.drawable.full_star);
            star2.setBackgroundResource(R.drawable.full_star);
            star3.setBackgroundResource(R.drawable.star_empty);
            star4.setBackgroundResource(R.drawable.star_empty);
            star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(rating_double) == 5){

            star1.setBackgroundResource(R.drawable.full_star);
            star2.setBackgroundResource(R.drawable.full_star);
            star3.setBackgroundResource(R.drawable.half_star_border);
            star3.setVisibility(View.VISIBLE);
            star4.setBackgroundResource(R.drawable.star_empty);
            star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(rating_double) == 6){

            star1.setBackgroundResource(R.drawable.full_star);
            star2.setBackgroundResource(R.drawable.full_star);
            star3.setBackgroundResource(R.drawable.full_star);
            star4.setBackgroundResource(R.drawable.star_empty);
            star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(rating_double) == 7){

            star1.setBackgroundResource(R.drawable.full_star);
            star2.setBackgroundResource(R.drawable.full_star);
            star3.setBackgroundResource(R.drawable.full_star);
            star4.setBackgroundResource(R.drawable.half_star_border);
            star4.setVisibility(View.VISIBLE);
            star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(rating_double) == 8){

            star1.setBackgroundResource(R.drawable.full_star);
            star2.setBackgroundResource(R.drawable.full_star);
            star3.setBackgroundResource(R.drawable.full_star);
            star4.setBackgroundResource(R.drawable.full_star);
            star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(rating_double) == 9){

            star1.setBackgroundResource(R.drawable.full_star);
            star2.setBackgroundResource(R.drawable.full_star);
            star3.setBackgroundResource(R.drawable.full_star);
            star4.setBackgroundResource(R.drawable.full_star);
            star5.setBackgroundResource(R.drawable.half_star_border);
            star5.setVisibility(View.VISIBLE);

        } else if ((int) Math.round(rating_double) == 10){

            star1.setBackgroundResource(R.drawable.full_star);
            star2.setBackgroundResource(R.drawable.full_star);
            star3.setBackgroundResource(R.drawable.full_star);
            star4.setBackgroundResource(R.drawable.full_star);
            star5.setBackgroundResource(R.drawable.full_star);

        }
        //Glide.with(this).load(Integer.parseInt(thumbnail)).into(album_cover);
        //album_name.setText(name);
        //number_songs.setText("Number of Songs: " + number);

    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public class MyAsync extends AsyncTask<Void, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(Void... params) {

            try {
                URL url = new URL("https://image.tmdb.org/t/p/w500" + backdrop);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }
    }

}
