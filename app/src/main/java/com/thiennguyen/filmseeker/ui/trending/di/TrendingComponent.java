package com.thiennguyen.filmseeker.ui.trending.di;

import com.thiennguyen.filmseeker.ui.trending.TrendingFragment;
import com.thiennguyen.filmseeker.ui.trending.TrendingPresenter;

import dagger.Subcomponent;

/**
 * Created by thien.nguyen on 10/12/18.
 */

@Subcomponent(modules = {TrendingModule.class})
public interface TrendingComponent {

  TrendingPresenter getTrendingPresenter();

  void inject(TrendingFragment trendingFragment);
}
