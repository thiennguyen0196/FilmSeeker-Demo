package com.thiennguyen.domain.service;

import com.thiennguyen.domain.model.MovieModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public interface DatabaseService {

  Observable<Void> deleteMoviesTrending();

  Observable<Void> deleteMoviesTopRated();

  Observable<List<? extends MovieModel>> getMoviesTrending();

  Observable<List<? extends MovieModel>> getMoviesTopRated();

  Observable<Void> saveMovies(List<? extends MovieModel> movies);
}
