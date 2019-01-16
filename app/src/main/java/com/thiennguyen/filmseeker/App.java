package com.thiennguyen.filmseeker;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.thiennguyen.filmseeker.di.AppComponent;
import com.thiennguyen.filmseeker.di.AppModule;
import com.thiennguyen.filmseeker.di.DaggerAppComponent;
import com.thiennguyen.filmseeker.di.LocalModule;
import com.thiennguyen.filmseeker.di.NetworkModule;
import com.thiennguyen.filmseeker.di.UseCaseModule;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import okhttp3.OkHttpClient;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public class App extends MultiDexApplication {

  private AppComponent mAppComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    FlowManager.init(new FlowConfig.Builder(this).build());
    FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);

    mAppComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .localModule(new LocalModule())
        .networkModule(
            new NetworkModule(getResources().getString(R.string.api_google_map_key), getResources().getString(R.string.api_movie_key)))
        .useCaseModule(new UseCaseModule())
        .build();

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .followRedirects(true)
        .build();

    ImagePipelineConfig imagePipelineConfig = OkHttpImagePipelineConfigFactory.newBuilder(this, okHttpClient)
        .build();

    Fresco.initialize(this, imagePipelineConfig);
  }

  public AppComponent getAppComponent() {
    return mAppComponent;
  }

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }
}
