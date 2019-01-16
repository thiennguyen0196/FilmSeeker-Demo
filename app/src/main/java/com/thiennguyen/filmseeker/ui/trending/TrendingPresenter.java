package com.thiennguyen.filmseeker.ui.trending;

import com.thiennguyen.domain.usecase.GetMoviesTrendingUseCase;
import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.utils.SchedulerProvider;
import com.thiennguyen.filmseeker.ui.base.Presenter;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public class TrendingPresenter extends Presenter<TrendingView> {

  private static final String TAG = TrendingPresenter.class.getSimpleName();

  private final GetMoviesTrendingUseCase mGetMoviesTrendingUseCase;

  private int mPage = Constant.Movie.PAGE_1;

  public TrendingPresenter(@NonNull TrendingView view, GetMoviesTrendingUseCase getMoviesTrendingUseCase) {
    super(view);
    mGetMoviesTrendingUseCase = getMoviesTrendingUseCase;
  }

  public void loadTrendingMovies() {
    setLoadingView(true, false);
    mPage = Constant.Movie.PAGE_1;
    mDisposables.add(mGetMoviesTrendingUseCase.getMoviesTrending(Constant.Movie.LANGUAGE, mPage)
        .compose(SchedulerProvider.applyScheduler())
        .subscribe(movies -> {
          getView().onDisableAdapterLoading();
          getView().onMoviesTrendingRefresh(movies);
          if (movies.size() > 0) {
            setLoadingView(false, false);
          } else {
            setLoadingView(false, true);
          }
          ++mPage;
        }, throwable -> {
          if (throwable != null) {
            Log.e(TAG, throwable.getMessage());
          }
          getView().onDisableAdapterLoading();
          setLoadingView(false, true);
        }));
  }

  public void loadTrendingMoviesMore() {
    getView().onAddItemNull();
    mDisposables.add(mGetMoviesTrendingUseCase.getMoviesTrending(Constant.Movie.LANGUAGE, mPage)
        .compose(SchedulerProvider.applyScheduler())
        .subscribe(movies -> {
          getView().onDisableAdapterLoading();
          getView().onRemoveItemNull();
          getView().onMoviesTrendingLoaded(movies);
          ++mPage;
        }, throwable -> {
          if (throwable != null) {
            Log.e(TAG, throwable.getMessage());
          }
          getView().onDisableAdapterLoading();
          getView().onRemoveItemNull();
        }));
  }

  public void refreshTrendingMovies() {
    setLoadingView(false, false);
    mDisposables.add(mGetMoviesTrendingUseCase.refreshMoviesTrending(Constant.Movie.LANGUAGE)
        .compose(SchedulerProvider.applyScheduler())
        .subscribe(movies -> {
          getView().onDisableAdapterLoading();
          getView().onDisableRefreshLayout();
          getView().onMoviesTrendingRefresh(movies);
          if (movies.size() > 0) {
            setLoadingView(false, false);
          } else {
            setLoadingView(false, true);
          }
          mPage = Constant.Movie.PAGE_1 + 1;
        }, throwable -> {
          if (throwable != null) {
            Log.e(TAG, throwable.getMessage());
          }
          getView().onDisableAdapterLoading();
          getView().onDisableRefreshLayout();
          setLoadingView(false, true);
        }));
  }

  private void setLoadingView(boolean isShowProgressBar, boolean isShowErrorLayout) {
    getView().onShowProgressBar(isShowProgressBar);
    getView().onShowErrorLayout(isShowErrorLayout);
  }
}
