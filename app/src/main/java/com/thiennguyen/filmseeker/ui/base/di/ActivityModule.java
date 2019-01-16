package com.thiennguyen.filmseeker.ui.base.di;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thien.nguyen on 10/11/18.
 */

@Module
public class ActivityModule {

  private final Activity mActivity;

  public ActivityModule(Activity activity) {
    this.mActivity = activity;
  }

  @Provides
  public Activity provideActivity() {
    return mActivity;
  }
}
