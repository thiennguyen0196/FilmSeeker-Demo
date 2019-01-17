package com.thiennguyen.filmseeker.ui.detail;

import com.thiennguyen.domain.usecase.GetMoviesSimilarUseCase;
import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.utils.SchedulerProvider;
import com.thiennguyen.filmseeker.ui.base.Presenter;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public class DetailMoviePresenter extends Presenter<DetailMovieView> {

  private static final String TAG = DetailMoviePresenter.class.getSimpleName();

  private final GetMoviesSimilarUseCase mGetMoviesSimilarUseCase;

  public DetailMoviePresenter(@NonNull DetailMovieView view,
      GetMoviesSimilarUseCase getMoviesSimilarUseCase) {
    super(view);
    mGetMoviesSimilarUseCase = getMoviesSimilarUseCase;
  }

  public void loadBySimilarMovie(long movieId) {
    mDisposables.add(mGetMoviesSimilarUseCase.getMoviesSimilar(movieId, Constant.Movie.LANGUAGE, Constant.Movie.PAGE_1)
        .compose(SchedulerProvider.applyScheduler())
        .subscribe(similarMovies -> getView().onSimilarMoviesLoaded(similarMovies)
            , throwable -> {
              if (throwable != null) {
                Log.e(TAG, throwable.getMessage());
              }
            }));
  }
}
