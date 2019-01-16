package com.thiennguyen.data.network.service;

import com.thiennguyen.data.network.model.ApiMovieModel;
import com.thiennguyen.data.network.response.PluralDataResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public interface HttpMovieService {

  @GET("3/movie/{id}/similar")
  Observable<PluralDataResponse<ApiMovieModel>> getMoviesSimilar(
      @Path("id") long id,
      @Query("language") String language,
      @Query("page") int page
  );

  @GET("3/movie/now_playing")
  Observable<PluralDataResponse<ApiMovieModel>> getMoviesTrending(
      @Query("language") String language,
      @Query("page") int page
  );

  @GET("3/movie/top_rated")
  Observable<PluralDataResponse<ApiMovieModel>> getMoviesTopRated(
      @Query("language") String language,
      @Query("page") int page
  );

}
