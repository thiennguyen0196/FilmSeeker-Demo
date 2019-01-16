package com.thiennguyen.filmseeker.ui.top_rated;

import com.thiennguyen.domain.usecase.GetMoviesTopRatedUseCase;
import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.utils.SchedulerProvider;
import com.thiennguyen.filmseeker.ui.base.Presenter;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public class TopRatedPresenter extends Presenter<TopRatedView> {

  private static final String TAG = TopRatedPresenter.class.getSimpleName();

  private final GetMoviesTopRatedUseCase mGetMoviesTopRatedUseCase;

  private int mPage = Constant.Movie.PAGE_1;

  public TopRatedPresenter(@NonNull TopRatedView view,
      GetMoviesTopRatedUseCase getMoviesTopRatedUseCase) {
    super(view);
    mGetMoviesTopRatedUseCase = getMoviesTopRatedUseCase;
  }

  public void loadTopRatedMovies() {
    setLoadingView(true, false);
    mPage = Constant.Movie.PAGE_1;
    mDisposables.add(mGetMoviesTopRatedUseCase.getMoviesTopRated(Constant.Movie.LANGUAGE, mPage)
        .compose(SchedulerProvider.applyScheduler())
        .subscribe(movies -> {
          getView().onDisableAdapterLoading();
          getView().onMoviesTopRatedRefresh(movies);
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

  public void loadTopRatedMoviesMore() {
    getView().onAddItemNull();
    mDisposables.add(mGetMoviesTopRatedUseCase.getMoviesTopRated(Constant.Movie.LANGUAGE, mPage)
        .compose(SchedulerProvider.applyScheduler())
        .subscribe(movies -> {
          getView().onDisableAdapterLoading();
          getView().onRemoveItemNull();
          getView().onMoviesTopRatedLoaded(movies);
          ++mPage;
        }, throwable -> {
          if (throwable != null) {
            Log.e(TAG, throwable.getMessage());
          }
          getView().onDisableAdapterLoading();
          getView().onRemoveItemNull();
        }));
  }

  public void refreshTopRatedMovies() {
    setLoadingView(false, false);
    mDisposables.add(mGetMoviesTopRatedUseCase.refreshMoviesTopRated(Constant.Movie.LANGUAGE)
        .compose(SchedulerProvider.applyScheduler())
        .subscribe(movies -> {
          getView().onDisableAdapterLoading();
          getView().onDisableRefreshLayout();
          getView().onMoviesTopRatedRefresh(movies);
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
