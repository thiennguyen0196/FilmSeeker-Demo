package com.thiennguyen.domain.usecase;

import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.domain.service.ApiService;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public class GetMoviesSimilarUseCase {

  private final ApiService mApiService;

  public GetMoviesSimilarUseCase(ApiService apiService) {
    mApiService = apiService;
  }

  public Observable<List<? extends MovieModel>> getMoviesSimilar(long id, String language, int page) {
    return mApiService.getMoviesSimilar(id, language, page);
  }

}
