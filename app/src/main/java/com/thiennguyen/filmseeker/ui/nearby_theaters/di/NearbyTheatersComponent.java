package com.thiennguyen.filmseeker.ui.nearby_theaters.di;

import com.thiennguyen.filmseeker.ui.nearby_theaters.NearbyTheatersFragment;
import com.thiennguyen.filmseeker.ui.nearby_theaters.NearbyTheatersPresenter;

import dagger.Subcomponent;

/**
 * Created by thien.nguyen on 10/12/18.
 */

@Subcomponent(modules = {NearbyTheatersModule.class})
public interface NearbyTheatersComponent {

  NearbyTheatersPresenter getNearbyTheatersPresenter();

  void inject(NearbyTheatersFragment nearbyTheatersFragment);
}
