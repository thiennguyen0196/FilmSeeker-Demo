package com.thiennguyen.domain.usecase;

import com.thiennguyen.domain.Constant;
import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.domain.service.ApiService;
import com.thiennguyen.domain.service.DatabaseService;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by thien.nguyen on 10/13/18.
 */

public class GetMoviesTopRatedUseCase {

  private final ApiService mApiService;

  private final DatabaseService mDatabaseService;

  public GetMoviesTopRatedUseCase(ApiService apiService, DatabaseService databaseService) {
    mApiService = apiService;
    mDatabaseService = databaseService;
  }

  public Observable<List<? extends MovieModel>> getMoviesTopRated(String language, int page) {
    if (page == Constant.Movie.PAGE_1) {
      return mApiService.getMoviesTopRated(language, Constant.Movie.PAGE_1)
          .onErrorReturn(throwable -> null)
          .flatMap(movies -> mDatabaseService.saveMovies(movies)
              .flatMap(aVoid -> mDatabaseService.getMoviesTopRated()));
    } else {
      return mApiService.getMoviesTopRated(language, page);
    }
  }

  public Observable<List<? extends MovieModel>> refreshMoviesTopRated(String language) {
    return mDatabaseService.deleteMoviesTopRated()
        .flatMap(aBoolean2 -> getMoviesTopRated(language, Constant.Movie.PAGE_1));
  }
}
