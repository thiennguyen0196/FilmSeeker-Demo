package com.thiennguyen.filmseeker.ui.base;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public abstract class Presenter<V extends BaseView> {

  private V view;

  protected CompositeDisposable mDisposables;

  public Presenter(@NonNull V view) {
    this.view = view;
    mDisposables = new CompositeDisposable();
  }

  public void detachView() {
    if (mDisposables!=null && !mDisposables.isDisposed()) {
      mDisposables.dispose();
      mDisposables = null;
    }
    this.view = null;
  }

  protected V getView() {
    return view;
  }
}
