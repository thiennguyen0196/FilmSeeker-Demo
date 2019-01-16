package com.thiennguyen.domain.service;

import com.thiennguyen.domain.model.TheaterModel;
import com.thiennguyen.domain.model.MovieModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public interface ApiService {

  Observable<List<? extends MovieModel>> getMoviesSimilar(long id, String language, int page);

  Observable<List<? extends MovieModel>> getMoviesTrending(String language, int page);

  Observable<List<? extends MovieModel>> getMoviesTopRated(String language, int page);

  Observable<List<? extends TheaterModel>> getTheatersNearby(String language, String location, String rankBy, String type);
}
