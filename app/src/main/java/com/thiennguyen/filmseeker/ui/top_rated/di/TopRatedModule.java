package com.thiennguyen.filmseeker.ui.top_rated.di;

import com.thiennguyen.domain.usecase.GetMoviesTopRatedUseCase;
import com.thiennguyen.filmseeker.ui.top_rated.TopRatedPresenter;
import com.thiennguyen.filmseeker.ui.top_rated.TopRatedView;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thien.nguyen on 10/12/18.
 */

@Module
public class TopRatedModule {

  private final TopRatedView mTopRatedView;

  public TopRatedModule(TopRatedView topRatedView) {
    mTopRatedView = topRatedView;
  }

  @Provides
  public TopRatedPresenter provideTopRatedPresenter(@NonNull TopRatedView view, GetMoviesTopRatedUseCase getMoviesTopRatedUseCase) {
    return new TopRatedPresenter(view, getMoviesTopRatedUseCase);
  }

  @Provides
  public TopRatedView provideTopRatedView() {
    return mTopRatedView;
  }

}
