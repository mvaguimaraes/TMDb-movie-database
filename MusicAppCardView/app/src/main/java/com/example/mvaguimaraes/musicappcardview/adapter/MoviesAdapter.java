package com.example.mvaguimaraes.musicappcardview.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvaguimaraes.musicappcardview.MovieDetails;
import com.example.mvaguimaraes.musicappcardview.R;
import com.example.mvaguimaraes.musicappcardview.model.MovieItem;

import java.util.List;

/**
 * Created by Mvaguimaraes on 8/23/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context mContext;
    private List<MovieItem> movieList;
    private CardView cardview;
    private MovieItem movie;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow, star1, star2, star3, star4, star5;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            star1 = (ImageView) view.findViewById(R.id.rating_image1);
            star2 = (ImageView) view.findViewById(R.id.rating_image2);
            star3 = (ImageView) view.findViewById(R.id.rating_image3);
            star4 = (ImageView) view.findViewById(R.id.rating_image4);
            star5 = (ImageView) view.findViewById(R.id.rating_image5);

            cardview = (CardView) view.findViewById(R.id.card_view);
            //overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public MoviesAdapter(Context mContext, List<MovieItem> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MoviesAdapter.MyViewHolder holder, final int position) {
        movie = movieList.get(position);
        holder.title.setText(movie.getTitle());
        holder.count.setText("R: " + movie.getVoteAverage().toString() + "/10");

        if ((int) Math.round(movie.getVoteAverage()) == 1){

            holder.star1.setBackgroundResource(R.drawable.half_star_border);
            holder.star1.setVisibility(View.VISIBLE);
            holder.star2.setBackgroundResource(R.drawable.star_empty);
            holder.star3.setBackgroundResource(R.drawable.star_empty);
            holder.star4.setBackgroundResource(R.drawable.star_empty);
            holder.star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(movie.getVoteAverage()) == 2){

            holder.star1.setBackgroundResource(R.drawable.full_star);
            holder.star2.setBackgroundResource(R.drawable.star_empty);
            holder.star3.setBackgroundResource(R.drawable.star_empty);
            holder.star4.setBackgroundResource(R.drawable.star_empty);
            holder.star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(movie.getVoteAverage()) == 3){

            holder.star1.setBackgroundResource(R.drawable.full_star);
            holder.star2.setBackgroundResource(R.drawable.half_star_border);
            holder.star2.setVisibility(View.VISIBLE);
            holder.star3.setBackgroundResource(R.drawable.star_empty);
            holder.star4.setBackgroundResource(R.drawable.star_empty);
            holder.star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(movie.getVoteAverage()) == 4){

            holder.star1.setBackgroundResource(R.drawable.full_star);
            holder.star2.setBackgroundResource(R.drawable.full_star);
            holder.star3.setBackgroundResource(R.drawable.star_empty);
            holder.star4.setBackgroundResource(R.drawable.star_empty);
            holder.star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(movie.getVoteAverage()) == 5){

            holder.star1.setBackgroundResource(R.drawable.full_star);
            holder.star2.setBackgroundResource(R.drawable.full_star);
            holder.star3.setBackgroundResource(R.drawable.half_star_border);
            holder.star3.setVisibility(View.VISIBLE);
            holder.star4.setBackgroundResource(R.drawable.star_empty);
            holder.star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(movie.getVoteAverage()) == 6){

            holder.star1.setBackgroundResource(R.drawable.full_star);
            holder.star2.setBackgroundResource(R.drawable.full_star);
            holder.star3.setBackgroundResource(R.drawable.full_star);
            holder.star4.setBackgroundResource(R.drawable.star_empty);
            holder.star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(movie.getVoteAverage()) == 7){

            holder.star1.setBackgroundResource(R.drawable.full_star);
            holder.star2.setBackgroundResource(R.drawable.full_star);
            holder.star3.setBackgroundResource(R.drawable.full_star);
            holder.star4.setBackgroundResource(R.drawable.half_star_border);
            holder.star4.setVisibility(View.VISIBLE);
            holder.star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(movie.getVoteAverage()) == 8){

            holder.star1.setBackgroundResource(R.drawable.full_star);
            holder.star2.setBackgroundResource(R.drawable.full_star);
            holder.star3.setBackgroundResource(R.drawable.full_star);
            holder.star4.setBackgroundResource(R.drawable.full_star);
            holder.star5.setBackgroundResource(R.drawable.star_empty);

        } else if ((int) Math.round(movie.getVoteAverage()) == 9){

            holder.star1.setBackgroundResource(R.drawable.full_star);
            holder.star2.setBackgroundResource(R.drawable.full_star);
            holder.star3.setBackgroundResource(R.drawable.full_star);
            holder.star4.setBackgroundResource(R.drawable.full_star);
            holder.star5.setBackgroundResource(R.drawable.half_star_border);
            holder.star5.setVisibility(View.VISIBLE);

        } else if ((int) Math.round(movie.getVoteAverage()) == 10){

            holder.star1.setBackgroundResource(R.drawable.full_star);
            holder.star2.setBackgroundResource(R.drawable.full_star);
            holder.star3.setBackgroundResource(R.drawable.full_star);
            holder.star4.setBackgroundResource(R.drawable.full_star);
            holder.star5.setBackgroundResource(R.drawable.full_star);

        }

        // loading album cover using Glide library
        //Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
        //System.out.println(movie.getPosterPath());
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath()).into(holder.thumbnail);

        /*holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });*/

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetails.class);
                intent.putExtra("movie_name", movieList.get(position).getTitle());
                intent.putExtra("movie_rating", movieList.get(position).getVoteAverage().toString());
                intent.putExtra("movie_description", movieList.get(position).getOverview());
                intent.putExtra("movie_cover", movieList.get(position).getPosterPath());
                intent.putExtra("movie_date", movieList.get(position).getReleaseDate());
                intent.putExtra("movie_language", movieList.get(position).getOriginalLanguage());
                intent.putExtra("movie_backdrop", movieList.get(position).getBackdropPath());
                intent.putExtra("id", movieList.get(position).getId().toString());
                //System.out.println("Adapter: " + movieList.get(position).getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.putExtra("album_name", albumList.get(position).getName());
                //intent.putExtra("album_thumbnail", Integer.toString(albumList.get(position).getThumbnail()));
                //intent.putExtra("number_songs", Integer.toString(albumList.get(position).getNumOfSongs()));

                mContext.startActivity(intent);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    /*private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MoviesAdapter.MyMenuItemClickListener());
        popup.show();
    }*/

    /**
     * Click listener for popup menu items
     */
    /*class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }*/

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}