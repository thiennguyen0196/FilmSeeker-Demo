package com.thiennguyen.data.network.service;

import com.thiennguyen.data.network.response.PluralDataResponse;
import com.thiennguyen.domain.model.TheaterModel;
import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.domain.model.MovieType;
import com.thiennguyen.domain.service.ApiService;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public class ApiServiceImpl implements ApiService {

  private final HttpMapService mHttpMapService;

  private final HttpMovieService mHttpMovieService;

  public ApiServiceImpl(HttpMapService httpMapService, HttpMovieService httpMovieService) {
    mHttpMovieService = httpMovieService;
    mHttpMapService = httpMapService;
  }

  @Override
  public Observable<List<? extends MovieModel>> getMoviesSimilar(long id, String language, int page) {
    return mHttpMovieService.getMoviesSimilar(id, language, page)
        .map(PluralDataResponse::getResults);
  }

  @Override
  public Observable<List<? extends MovieModel>> getMoviesTrending(String language, int page) {
    return mHttpMovieService.getMoviesTrending(language, page)
        .map(PluralDataResponse::getResults)
        .flatMap(movies -> Observable.fromIterable(movies)
            .map(movie -> movie)
            .flatMap(movie -> {
              movie.setType(MovieType.TRENDING);
              return Observable.just(movie);
            }).toList().toObservable());
  }

  @Override
  public Observable<List<? extends MovieModel>> getMoviesTopRated(String language, int page) {
    return mHttpMovieService.getMoviesTopRated(language, page)
        .map(PluralDataResponse::getResults)
        .flatMap(movies -> Observable.fromIterable(movies)
            .map(movie -> movie)
            .flatMap(movie -> {
              movie.setType(MovieType.TOP_RATED);
              return Observable.just(movie);
            }).toList().toObservable());
  }

  @Override
  public Observable<List<? extends TheaterModel>> getTheatersNearby(String language, String location, String rankBy, String type) {
    return mHttpMapService.getTheatersNearby(language, location, rankBy, type)
        .map(PluralDataResponse::getResults);
  }
}
