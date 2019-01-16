package com.thiennguyen.filmseeker.di;

import com.thiennguyen.filmseeker.ui.base.di.ActivityComponent;
import com.thiennguyen.filmseeker.ui.base.di.ActivityModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by thien.nguyen on 10/11/18.
 */

@Singleton
@Component(modules = {AppModule.class, LocalModule.class, NetworkModule.class, UseCaseModule.class})
public interface AppComponent {

  ActivityComponent plus(ActivityModule activityModule);
}
