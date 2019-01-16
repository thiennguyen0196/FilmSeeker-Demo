package com.thiennguyen.domain.usecase;

import com.thiennguyen.domain.Constant;
import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.domain.service.ApiService;
import com.thiennguyen.domain.service.DatabaseService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public class GetMoviesTrendingUseCase {

  private final ApiService mApiService;

  private final DatabaseService mDatabaseService;

  public GetMoviesTrendingUseCase(ApiService apiService, DatabaseService databaseService) {
    mApiService = apiService;
    mDatabaseService = databaseService;
  }

  public Observable<List<? extends MovieModel>> getMoviesTrending(String language, int page) {
    if (page == Constant.Movie.PAGE_1) {
      return mApiService.getMoviesTrending(language, Constant.Movie.PAGE_1)
          .onErrorReturn(throwable -> null)
          .flatMap(movies -> mDatabaseService.saveMovies(movies)
              .flatMap(aVoid -> mDatabaseService.getMoviesTrending()));
    } else {
      return mApiService.getMoviesTrending(language, page);
    }
  }

  public Observable<List<? extends MovieModel>> refreshMoviesTrending(String language) {
    return mDatabaseService.deleteMoviesTrending()
        .flatMap(aBoolean2 -> getMoviesTrending(language, Constant.Movie.PAGE_1));
  }
}
