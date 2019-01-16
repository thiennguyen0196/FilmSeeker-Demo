package com.thiennguyen.filmseeker.holder;

import com.facebook.drawee.view.SimpleDraweeView;
import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.R;
import com.thiennguyen.filmseeker.listener.OnItemClickListener;
import com.thiennguyen.filmseeker.ui.MovieLayout;
import com.thiennguyen.filmseeker.utils.DateUtils;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by thien.nguyen on 1/14/19.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

  private static final String TAG = MovieViewHolder.class.getSimpleName();

  private MovieModel mMovie;

  private SimpleDraweeView vImgPoster;

  private TextView vTxtOverview;

  private TextView vTxtReleaseDate;

  private TextView vTxtTitle;

  private TextView vTxtVoteAverage;

  public MovieViewHolder(View itemView, OnItemClickListener itemClickListener) {
    super(itemView);

    vImgPoster = itemView.findViewById(R.id.imgPoster);
    vTxtOverview = itemView.findViewById(R.id.txtOverview);
    vTxtReleaseDate = itemView.findViewById(R.id.txtReleaseDate);
    vTxtTitle = itemView.findViewById(R.id.txtTitle);
    vTxtVoteAverage = itemView.findViewById(R.id.txtVoteAverage);
    itemView.setOnClickListener(v -> {
      if (itemClickListener != null) {
        itemClickListener.onItemClick(mMovie);
      }
    });
  }

  public void bind(MovieModel movie, MovieLayout movieLayout) {
    if (movie == null) {
      Log.i(TAG, "MOVIE MODEL >>>>>>>>>> NULL");
      return;
    }
    Log.i(TAG, String.valueOf(movie));
    mMovie = movie;
    vTxtTitle.setText(movie.getTitle());
    Uri pathUri = Uri.parse(String.format(Constant.Movie.URL_POSTER, movie.getPosterPath()));
    if (pathUri != Uri.EMPTY) {
      vImgPoster.setImageURI(pathUri);
    } else {
      vImgPoster.setImageURI("");
    }
    if (movieLayout == MovieLayout.VERTICAL) {
      vTxtReleaseDate.setText(DateUtils
          .formatDateType(Constant.DateTime.DATE_INPUT_FORMAT, Constant.DateTime.DATE_OUTPUT_FORMAT, movie.getReleaseDate()));
      vTxtOverview.setText(movie.getOverview());
      vTxtVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
    }
  }
}