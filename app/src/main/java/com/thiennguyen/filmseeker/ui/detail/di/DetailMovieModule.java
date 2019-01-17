package com.thiennguyen.filmseeker.ui.detail.di;

import com.thiennguyen.domain.usecase.GetMoviesSimilarUseCase;
import com.thiennguyen.filmseeker.ui.detail.DetailMoviePresenter;
import com.thiennguyen.filmseeker.ui.detail.DetailMovieView;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thien.nguyen on 10/12/18.
 */

@Module
public class DetailMovieModule {

  private DetailMovieView mDetailMovieView;

  public DetailMovieModule(DetailMovieView detailMovieView) {
    mDetailMovieView = detailMovieView;
  }

  @Provides
  public DetailMoviePresenter provideDetailMoviePresenter(@NonNull DetailMovieView view, GetMoviesSimilarUseCase getMoviesSimilarUseCase) {
    return new DetailMoviePresenter(view, getMoviesSimilarUseCase);
  }

  @Provides
  public DetailMovieView provideDetailMovieView() {
    return mDetailMovieView;
  }
}
