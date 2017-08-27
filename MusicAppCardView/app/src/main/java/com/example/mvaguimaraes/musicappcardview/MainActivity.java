package com.example.mvaguimaraes.musicappcardview;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mvaguimaraes.musicappcardview.adapter.MoviesAdapter;
import com.example.mvaguimaraes.musicappcardview.model.MovieGenre;
import com.example.mvaguimaraes.musicappcardview.model.MovieItem;
import com.example.mvaguimaraes.musicappcardview.model.MoviesResponse;
import com.example.mvaguimaraes.musicappcardview.rest.ApiClient;
import com.example.mvaguimaraes.musicappcardview.rest.ApiInterface;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "ff75e7ade3bd186765380cb5bb77eff9";
    Activity context;
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private List<MovieItem> movies;
    private List<MovieGenre> genres;
    private static final String TAG = "MainActivity";
    private FloatingActionButton b1,b2,b3,b4,menu;
    private ApiInterface apiService;
    private TextView title, subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        b1 = (FloatingActionButton) findViewById(R.id.TopRated);
        b2 = (FloatingActionButton) findViewById(R.id.Upcoming);
        b3 = (FloatingActionButton) findViewById(R.id.NowPlaying);
        b4 = (FloatingActionButton) findViewById(R.id.Popular);

        title = (TextView) findViewById(R.id.title);
        subtitle = (TextView) findViewById(R.id.subtitle);

        title.setText("POPULAR MOVIES");
        subtitle.setText("Most popular movies nowadays");

        if (API_KEY.isEmpty()) {
            Toast.makeText(context, "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        initCollapsingToolbar();

        //prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.movie_cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();

                movies = new ArrayList<>();
                movies = response.body().getResults();
                if (movies.isEmpty()){
                    System.out.println("Tá vazio");
                    Toast.makeText(getApplicationContext(), "Nothing was found! =[",
                            Toast.LENGTH_LONG).show();
                }
                adapter = new MoviesAdapter(getApplicationContext(), movies);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        /*apiService2 =
                ApiClient.getClient().create(ApiInterface.class);

        Call<GenreResponse> call2 = apiService.getGenres(API_KEY);
        call2 = apiService2.getGenres(API_KEY);
        call2.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                int statusCode = response.code();

                genres = new ArrayList<>();
                genres = response.body().getResults();
                System.out.println(response.body().getResults());

            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });*/

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
                call.enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        int statusCode = response.code();

                        movies = new ArrayList<>();
                        movies = response.body().getResults();
                        if (movies.isEmpty()){
                            System.out.println("Tá vazio");
                            Toast.makeText(getApplicationContext(), "Nothing was found! =[",
                                    Toast.LENGTH_LONG).show();
                        }

                        title.setText("TOP RATED MOVIES");
                        subtitle.setText("Best movies of all times");

                        adapter = new MoviesAdapter(getApplicationContext(), movies);
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        // Log error here since request failed
                        Log.e(TAG, t.toString());
                    }
                });

            }
        });

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Call<MoviesResponse> call = apiService.getUpcomingMovies(API_KEY);
                call.enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        int statusCode = response.code();

                        movies = new ArrayList<>();
                        movies = response.body().getResults();
                        if (movies.isEmpty()){
                            System.out.println("Tá vazio");
                            Toast.makeText(getApplicationContext(), "Nothing was found! =[",
                                    Toast.LENGTH_LONG).show();
                        }

                        title.setText("UPCOMING MOVIES");
                        subtitle.setText("Coming soon in the theaters");

                        adapter = new MoviesAdapter(getApplicationContext(), movies);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        // Log error here since request failed
                        Log.e(TAG, t.toString());
                    }
                });

            }
        });

        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Call<MoviesResponse> call = apiService.getNowPlayingMovies(API_KEY);
                call.enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        int statusCode = response.code();

                        movies = new ArrayList<>();
                        movies = response.body().getResults();
                        if (movies.isEmpty()){
                            System.out.println("Tá vazio");
                            Toast.makeText(getApplicationContext(), "Nothing was found! =[",
                                    Toast.LENGTH_LONG).show();
                        }

                        title.setText("NOW PLAYING MOVIES");
                        subtitle.setText("Playing in the theaters now");

                        adapter = new MoviesAdapter(getApplicationContext(), movies);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        // Log error here since request failed
                        Log.e(TAG, t.toString());
                    }
                });

            }
        });

        b4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Call<MoviesResponse> call = apiService.getPopularMovies(API_KEY);
                call.enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        int statusCode = response.code();

                        movies = new ArrayList<>();
                        movies = response.body().getResults();
                        if (movies.isEmpty()){
                            System.out.println("Tá vazio");
                            Toast.makeText(getApplicationContext(), "Nothing was found! =[",
                                    Toast.LENGTH_LONG).show();
                        }

                        title.setText("POPULAR MOVIES");
                        subtitle.setText("Most popular movies nowadays");

                        adapter = new MoviesAdapter(getApplicationContext(), movies);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        // Log error here since request failed
                        Log.e(TAG, t.toString());
                    }
                });

            }
        });

        /*ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Intent intent = new Intent(getBaseContext(), MovieDetails.class);
                intent.putExtra("movie_name", movies.get(position).getTitle());
                intent.putExtra("movie_rating", movies.get(position).getVoteAverage().toString());
                intent.putExtra("movie_description", movies.get(position).getOverview());
                intent.putExtra("movie_cover", movies.get(position).getPosterPath());
                intent.putExtra("movie_date", movies.get(position).getReleaseDate());
                intent.putExtra("movie_language", movies.get(position).getOriginalLanguage());
                intent.putExtra("movie_backdrop", movies.get(position).getBackdropPath());
                //intent.putExtra("album_name", albumList.get(position).getName());
                //intent.putExtra("album_thumbnail", Integer.toString(albumList.get(position).getThumbnail()));
                //intent.putExtra("number_songs", Integer.toString(albumList.get(position).getNumOfSongs()));

                startActivity(intent);
            }
        });*/
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}