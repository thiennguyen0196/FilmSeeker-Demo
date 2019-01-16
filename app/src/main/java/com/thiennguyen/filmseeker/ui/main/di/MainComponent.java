package com.thiennguyen.filmseeker.ui.main.di;

import com.thiennguyen.filmseeker.ui.main.MainActivity;
import com.thiennguyen.filmseeker.ui.main.MainPresenter;

import dagger.Subcomponent;

/**
 * Created by thien.nguyen on 10/17/18.
 */

@Subcomponent(modules = {MainModule.class})
public interface MainComponent {

  MainPresenter getMainPresenter();

  void inject(MainActivity mainActivity);

}
