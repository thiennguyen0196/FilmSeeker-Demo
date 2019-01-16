package com.thiennguyen.filmseeker.ui.main.di;

import com.thiennguyen.filmseeker.ui.main.MainPresenter;
import com.thiennguyen.filmseeker.ui.main.MainView;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thien.nguyen on 10/17/18.
 */

@Module
public class MainModule {

  private final MainView mMainView;

  public MainModule(MainView mainView) {
    mMainView = mainView;
  }

  @Provides
  public MainPresenter provideMainPresenter(@NonNull MainView view) {
    return new MainPresenter(view);
  }

  @Provides
  public MainView provideMainView() {
    return mMainView;
  }
}
