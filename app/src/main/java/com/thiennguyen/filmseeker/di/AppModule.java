package com.thiennguyen.filmseeker.di;

import com.thiennguyen.filmseeker.App;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thien.nguyen on 10/11/18.
 */

@Module
public class AppModule {

  private final App app;

  public AppModule(App app) {
    this.app = app;
  }

  @Provides
  @Singleton
  public Context provideContext() {
    return app;
  }
}
