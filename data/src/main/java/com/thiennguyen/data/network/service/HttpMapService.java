package com.thiennguyen.data.network.service;

import com.thiennguyen.data.network.model.ApiTheaterModel;
import com.thiennguyen.data.network.response.PluralDataResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by thien.nguyen on 10/22/18.
 */

public interface HttpMapService {

  @GET("place/nearbysearch/json?")
  Observable<PluralDataResponse<ApiTheaterModel>> getTheatersNearby(
      @Query("language") String language,
      @Query("location") String location,
      @Query("rankby") String rankby,
      @Query("type") String type
  );
}
