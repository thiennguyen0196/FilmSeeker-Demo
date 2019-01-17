package com.thiennguyen.filmseeker.ui.top_rated.di;

import com.thiennguyen.filmseeker.ui.top_rated.TopRatedFragment;
import com.thiennguyen.filmseeker.ui.top_rated.TopRatedPresenter;

import dagger.Subcomponent;

/**
 * Created by thien.nguyen on 10/12/18.
 */

@Subcomponent(modules = {TopRatedModule.class})
public interface TopRatedComponent {

  TopRatedPresenter getTopRatedPresenter();

  void inject(TopRatedFragment topRatedFragment);
}
