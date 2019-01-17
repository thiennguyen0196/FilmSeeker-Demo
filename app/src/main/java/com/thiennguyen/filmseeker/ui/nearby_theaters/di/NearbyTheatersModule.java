package com.thiennguyen.filmseeker.ui.nearby_theaters.di;

import com.thiennguyen.domain.usecase.GetTheatersNearbyUseCase;
import com.thiennguyen.filmseeker.ui.nearby_theaters.NearbyTheatersPresenter;
import com.thiennguyen.filmseeker.ui.nearby_theaters.NearbyTheatersView;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thien.nguyen on 10/12/18.
 */

@Module
public class NearbyTheatersModule {

  private final NearbyTheatersView mNearbyTheatersView;

  public NearbyTheatersModule(NearbyTheatersView nearbyTheatersView) {
    mNearbyTheatersView = nearbyTheatersView;
  }

  @Provides
  public NearbyTheatersPresenter provideNearbyTheatersPresenter(@NonNull NearbyTheatersView view,
      GetTheatersNearbyUseCase getTheatersNearbyUseCase) {
    return new NearbyTheatersPresenter(view, getTheatersNearbyUseCase);
  }

  @Provides
  public NearbyTheatersView provideNearbyTheatersView() {
    return mNearbyTheatersView;
  }
}
