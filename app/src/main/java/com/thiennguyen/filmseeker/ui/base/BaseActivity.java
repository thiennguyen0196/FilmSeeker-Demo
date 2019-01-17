package com.thiennguyen.filmseeker.ui.base;

import com.thiennguyen.filmseeker.App;
import com.thiennguyen.filmseeker.di.AppComponent;
import com.thiennguyen.filmseeker.ui.base.di.ActivityComponent;
import com.thiennguyen.filmseeker.ui.base.di.ActivityModule;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.henrytao.mdcore.core.MdCore;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

  private ActivityComponent mActivityComponent;

  public ActivityComponent getActivityComponent() {
    return mActivityComponent;
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    MdCore.init(this);
    super.onCreate(savedInstanceState);

    setContentView(getLayoutResID());
    AppComponent appComponent = ((App) this.getApplication()).getAppComponent();
    mActivityComponent = appComponent.plus(new ActivityModule(this));
  }

  @LayoutRes
  protected abstract int getLayoutResID();
}
