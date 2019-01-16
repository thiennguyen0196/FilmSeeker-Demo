package com.thiennguyen.filmseeker.ui.nearby_theaters;

import com.thiennguyen.domain.usecase.GetTheatersNearbyUseCase;
import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.utils.SchedulerProvider;
import com.thiennguyen.filmseeker.ui.base.Presenter;

import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public class NearbyTheatersPresenter extends Presenter<NearbyTheatersView> {

  private final String TAG = NearbyTheatersPresenter.class.getSimpleName();

  private final GetTheatersNearbyUseCase mGetTheatersNearbyUseCase;

  public NearbyTheatersPresenter(@NonNull NearbyTheatersView view, GetTheatersNearbyUseCase getTheatersNearbyUseCase) {
    super(view);
    mGetTheatersNearbyUseCase = getTheatersNearbyUseCase;
  }

  public void loadNearbyTheaters(Location curLocation) {
    getView().onShowProgressDialog(true);
    if (curLocation == null) {
      getView().onShowProgressDialog(false);
    } else {
      String location = String.format("%s,%s", curLocation.getLatitude(), curLocation.getLongitude());
      mDisposables.add(mGetTheatersNearbyUseCase
          .getNearbyTheaters(Constant.Map.LANGUAGE, location, Constant.Map.LOCATION_RANK_BY, Constant.Map.LOCATION_TYPE)
          .compose(SchedulerProvider.applyScheduler())
          .subscribe(theaters -> {
            getView().onTheaterLoaded(theaters);
            getView().onShowProgressDialog(false);
          }, throwable -> {
            Log.e(TAG, throwable.getMessage());
            getView().onTheaterLoaded(new ArrayList<>());
            getView().onShowProgressDialog(false);
          }));
    }
  }
}
