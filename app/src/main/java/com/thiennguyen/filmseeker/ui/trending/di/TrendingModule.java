package com.thiennguyen.filmseeker.ui.trending.di;

import com.thiennguyen.domain.usecase.GetMoviesTrendingUseCase;
import com.thiennguyen.filmseeker.ui.trending.TrendingPresenter;
import com.thiennguyen.filmseeker.ui.trending.TrendingView;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thien.nguyen on 10/12/18.
 */

@Module
public class TrendingModule {

  private final TrendingView mTrendingView;

  public TrendingModule(TrendingView trendingView) {
    mTrendingView = trendingView;
  }

  @Provides
  public TrendingPresenter provideTrendingPresenter(@NonNull TrendingView view, GetMoviesTrendingUseCase getMoviesTrendingUseCase) {
    return new TrendingPresenter(view, getMoviesTrendingUseCase);
  }

  @Provides
  public TrendingView provideTrendingView() {
    return mTrendingView;
  }
}
