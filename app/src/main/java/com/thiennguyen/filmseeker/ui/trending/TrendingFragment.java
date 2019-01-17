package com.thiennguyen.filmseeker.ui.trending;

import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.R;
import com.thiennguyen.filmseeker.listener.OnItemClickListener;
import com.thiennguyen.filmseeker.listener.OnLoadMoreListener;
import com.thiennguyen.filmseeker.ui.base.di.ActivityComponent;
import com.thiennguyen.filmseeker.adapter.MovieAdapter;
import com.thiennguyen.filmseeker.ui.base.BaseActivity;
import com.thiennguyen.filmseeker.ui.base.MvpFragment;
import com.thiennguyen.filmseeker.ui.detail.DetailMovieActivity;
import com.thiennguyen.filmseeker.ui.trending.di.TrendingComponent;
import com.thiennguyen.filmseeker.ui.trending.di.TrendingModule;
import com.thiennguyen.filmseeker.ui.MovieLayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public class TrendingFragment extends MvpFragment<TrendingPresenter> implements TrendingView, OnItemClickListener<MovieModel>,
    OnLoadMoreListener {

  private static final String TAG = TrendingFragment.class.getSimpleName();

  private TrendingComponent mTrendingComponent;

  private MovieAdapter mMovieAdapter;

  private View vLayoutError;

  private ProgressBar vProgressBar;

  private RecyclerView vListMovies;

  private SwipeRefreshLayout vSwipeRefreshLayout;

  public static TrendingFragment newInstance() {
    return new TrendingFragment();
  }

  @Override
  public void onAddItemNull() {
    mMovieAdapter.addLoading();
  }

  @Override
  public void onDisableAdapterLoading() {
    mMovieAdapter.disableLoading();
  }

  @Override
  public void onDisableRefreshLayout() {
    vSwipeRefreshLayout.setRefreshing(false);
  }

  @Override
  public void onItemClick(MovieModel movie) {
    startActivity(DetailMovieActivity.newIntent(getActivity(), movie));
  }

  @Override
  public void onLoadMore() {
    presenter.loadTrendingMoviesMore();
  }

  @Override
  public void onMoviesTrendingLoaded(List<? extends MovieModel> movies) {
    mMovieAdapter.addMovies(movies);
  }

  @Override
  public void onMoviesTrendingRefresh(List<? extends MovieModel> movies) {
    mMovieAdapter.setMovies(movies);
  }

  @Override
  public void onShowErrorLayout(boolean isVisible) {
    vLayoutError.setVisibility(isVisible ? View.VISIBLE : View.GONE);
  }

  @Override
  public void onShowProgressBar(boolean isShowProgressBar) {
    vProgressBar.setVisibility(isShowProgressBar ? View.VISIBLE : View.GONE);
  }

  @Override
  public void onRemoveItemNull() {
    mMovieAdapter.removeLoading();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    setHasOptionsMenu(true);
    mMovieAdapter = new MovieAdapter(vListMovies, MovieLayout.STAGGERED, this, this);
    vListMovies.setLayoutManager(new StaggeredGridLayoutManager(Constant.Movie.STAGGERED_SPAN_COUNT, Constant.Movie.STAGGERED_ORIENTATION));
    vListMovies.setItemAnimator(null);
    vListMovies.setAdapter(mMovieAdapter);
    vSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
    presenter.loadTrendingMovies();
    onLoad();
  }

  @Override
  protected void buildComponent() {
    Log.v(TAG, "buildComponent");
    ActivityComponent activityComponent = ((BaseActivity) getActivity()).getActivityComponent();
    if (activityComponent != null) {
      mTrendingComponent = activityComponent.plus(new TrendingModule(this));
      mTrendingComponent.inject(this);
    }
  }

  @Override
  protected void findViewById(View v) {
    vProgressBar = v.findViewById(R.id.progressBar);
    vLayoutError = v.findViewById(R.id.layoutError);
    vListMovies = v.findViewById(R.id.listMovies);
    vSwipeRefreshLayout = v.findViewById(R.id.layoutRefresh);
    vLayoutError.bringToFront();
  }

  @Override
  protected int getLayoutResID() {
    return R.layout.fragment_trending;
  }

  @Override
  protected TrendingPresenter getPresenter() {
    return mTrendingComponent.getTrendingPresenter();
  }

  private void onLoad() {
    vSwipeRefreshLayout.setOnRefreshListener(() -> presenter.refreshTrendingMovies());
    vLayoutError.setOnClickListener(v -> presenter.loadTrendingMovies());
  }
}
