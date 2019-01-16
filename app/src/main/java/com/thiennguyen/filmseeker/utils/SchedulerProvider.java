package com.thiennguyen.filmseeker.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public class SchedulerProvider {

  public static <T> ObservableTransformer<T, T> applyScheduler() {
    return observable -> observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
