package com.thiennguyen.filmseeker.ui.base.di;

import com.thiennguyen.filmseeker.di.ActivityScope;
import com.thiennguyen.filmseeker.ui.detail.di.DetailMovieComponent;
import com.thiennguyen.filmseeker.ui.detail.di.DetailMovieModule;
import com.thiennguyen.filmseeker.ui.main.di.MainComponent;
import com.thiennguyen.filmseeker.ui.main.di.MainModule;
import com.thiennguyen.filmseeker.ui.nearby_theaters.di.NearbyTheatersComponent;
import com.thiennguyen.filmseeker.ui.nearby_theaters.di.NearbyTheatersModule;
import com.thiennguyen.filmseeker.ui.top_rated.di.TopRatedComponent;
import com.thiennguyen.filmseeker.ui.top_rated.di.TopRatedModule;
import com.thiennguyen.filmseeker.ui.trending.di.TrendingComponent;
import com.thiennguyen.filmseeker.ui.trending.di.TrendingModule;

import dagger.Subcomponent;

/**
 * Created by thien.nguyen on 10/11/18.
 */

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

  DetailMovieComponent plus(DetailMovieModule detailMovieModule);

  MainComponent plus(MainModule mainModule);

  NearbyTheatersComponent plus(NearbyTheatersModule nearbyTheatersModule);

  TrendingComponent plus(TrendingModule trendingModule);

  TopRatedComponent plus(TopRatedModule topRatedModule);
}
