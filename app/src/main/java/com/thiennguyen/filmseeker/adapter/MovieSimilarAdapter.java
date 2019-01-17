package com.thiennguyen.filmseeker.adapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.R;
import com.thiennguyen.filmseeker.holder.MovieSimilarViewHolder;
import com.thiennguyen.filmseeker.listener.OnItemClickListener;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public class MovieSimilarAdapter extends RecyclerView.Adapter<MovieSimilarViewHolder> {

  private final OnItemClickListener mItemClickListener;

  private final List<MovieModel> mMovies;

  public MovieSimilarAdapter(OnItemClickListener itemClickListener) {
    mMovies = new ArrayList<>();
    mItemClickListener = itemClickListener;
  }

  @Override
  public int getItemCount() {
    return mMovies == null ? 0 : mMovies.size();
  }

  @Override
  public void onBindViewHolder(MovieSimilarViewHolder holder, int position) {
    holder.bind(mMovies.get(position));
  }

  @Override
  public MovieSimilarViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    return new MovieSimilarViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_similar, viewGroup, false),
        mItemClickListener);
  }

  public void setMovies(List<? extends MovieModel> movies) {
    mMovies.clear();
    if (!movies.isEmpty()) {
      mMovies.addAll(movies);
    }
    notifyDataSetChanged();
  }
}
