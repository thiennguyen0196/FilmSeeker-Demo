package com.thiennguyen.domain.usecase;

import com.thiennguyen.domain.model.TheaterModel;
import com.thiennguyen.domain.service.ApiService;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by thien.nguyen on 10/22/18.
 */

public class GetTheatersNearbyUseCase {

  private final ApiService mApiService;

  public GetTheatersNearbyUseCase(ApiService apiService) {
    mApiService = apiService;
  }

  public Observable<List<? extends TheaterModel>> getNearbyTheaters(String language, String location, String rankby, String type) {
    return mApiService.getTheatersNearby(language, location, rankby, type);
  }
}
