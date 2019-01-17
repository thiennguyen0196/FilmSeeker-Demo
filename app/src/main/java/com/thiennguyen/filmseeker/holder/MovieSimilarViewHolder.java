package com.thiennguyen.filmseeker.holder;

import com.facebook.drawee.view.SimpleDraweeView;
import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.R;
import com.thiennguyen.filmseeker.listener.OnItemClickListener;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by thien.nguyen on 1/14/19.
 */

public class MovieSimilarViewHolder extends RecyclerView.ViewHolder {

  private static final String TAG = MovieSimilarViewHolder.class.getSimpleName();

  private MovieModel mMovie;

  private SimpleDraweeView vImgPoster;

  public MovieSimilarViewHolder(View itemView, OnItemClickListener itemClickListener) {
    super(itemView);

    vImgPoster = itemView.findViewById(R.id.imgPoster);
    itemView.setOnClickListener(v -> {
      if (itemClickListener != null) {
        itemClickListener.onItemClick(mMovie);
      }
    });
  }

  public void bind(MovieModel movie) {
    if (movie == null) {
      Log.i(TAG, "MOVIE MODEL >>>>>>>>>> NULL");
      return;
    }
    mMovie = movie;
    Uri pathUri = Uri.parse(String.format(Constant.Movie.URL_POSTER, movie.getPosterPath()));
    if (pathUri != Uri.EMPTY) {
      vImgPoster.setImageURI(pathUri);
    } else {
      vImgPoster.setImageURI("");
    }
  }
}
