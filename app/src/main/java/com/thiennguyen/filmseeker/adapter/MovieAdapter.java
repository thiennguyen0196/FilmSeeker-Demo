package com.thiennguyen.filmseeker.adapter;

import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.R;
import com.thiennguyen.filmseeker.holder.LoadingViewHolder;
import com.thiennguyen.filmseeker.holder.MovieViewHolder;
import com.thiennguyen.filmseeker.listener.OnItemClickListener;
import com.thiennguyen.filmseeker.listener.OnLoadMoreListener;
import com.thiennguyen.filmseeker.ui.MovieLayout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final OnItemClickListener mItemClickListener;

  private final OnLoadMoreListener mOnLoadMoreListener;

  private final List<MovieModel> mMovies;

  private final MovieLayout mMovieLayout;

  private boolean mIsLoading = false;

  private int mPosition;

  public MovieAdapter(RecyclerView recyclerView, MovieLayout movieLayout, OnItemClickListener itemClickListener,
      OnLoadMoreListener loadMoreListener) {
    mMovies = new ArrayList<>();
    mMovieLayout = movieLayout;
    mItemClickListener = itemClickListener;
    mOnLoadMoreListener = loadMoreListener;
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0 && !mIsLoading && mPosition == (getItemCount() - 1) && mOnLoadMoreListener != null) {
          mIsLoading = true;
          mOnLoadMoreListener.onLoadMore();
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mMovies == null ? 0 : mMovies.size();
  }

  @Override
  public int getItemViewType(int position) {
    return mMovies.get(position) == null ? Constant.ViewType.LOADING : Constant.ViewType.ITEM;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof MovieViewHolder) {
      ((MovieViewHolder) holder).bind(mMovies.get(position), mMovieLayout);
    }
    this.mPosition = position;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    if (viewType == Constant.ViewType.LOADING) {
      return new LoadingViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_loading, viewGroup, false));
    } else if (viewType == Constant.ViewType.ITEM) {
      return new MovieViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(mMovieLayout.getValue(), viewGroup, false),
          mItemClickListener);
    }
    return null;
  }

  public void addLoading() {
    mMovies.add(null);
    if (!mMovies.isEmpty()) {
      notifyItemInserted(mMovies.size() - 1);
    }
  }

  public void addMovies(List<? extends MovieModel> movies) {
    if (movies != null) {
      int size = mMovies.size();
      mMovies.addAll(movies);
      notifyItemRangeInserted(size, mMovies.size());
    }
  }

  public void disableLoading() {
    this.mIsLoading = false;
  }

  public void removeLoading() {
    mMovies.remove(mMovies.size() - 1);
    notifyItemRemoved(mMovies.size());
  }

  public void setMovies(List<? extends MovieModel> movies) {
    mMovies.clear();
    if (!movies.isEmpty()) {
      mMovies.addAll(movies);
    }
    notifyDataSetChanged();
  }
}
