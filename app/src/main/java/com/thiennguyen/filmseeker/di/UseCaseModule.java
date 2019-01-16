package com.thiennguyen.filmseeker.di;

import com.thiennguyen.domain.service.ApiService;
import com.thiennguyen.domain.service.DatabaseService;
import com.thiennguyen.domain.usecase.GetMoviesSimilarUseCase;
import com.thiennguyen.domain.usecase.GetMoviesTopRatedUseCase;
import com.thiennguyen.domain.usecase.GetMoviesTrendingUseCase;
import com.thiennguyen.domain.usecase.GetTheatersNearbyUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thien.nguyen on 10/11/18.
 */

@Module
public class UseCaseModule {

  @Provides
  @Singleton
  public GetMoviesSimilarUseCase provideGetMoviesSimilarUseCase(ApiService apiService) {
    return new GetMoviesSimilarUseCase(apiService);
  }

  @Provides
  @Singleton
  public GetMoviesTrendingUseCase provideGetMoviesTrendingUseCase(ApiService apiService, DatabaseService databaseService) {
    return new GetMoviesTrendingUseCase(apiService, databaseService);
  }

  @Provides
  @Singleton
  public GetMoviesTopRatedUseCase provideGetMoviesTopRatedUseCase(ApiService apiService, DatabaseService databaseService) {
    return new GetMoviesTopRatedUseCase(apiService, databaseService);
  }

  @Provides
  @Singleton
  public GetTheatersNearbyUseCase provideGetTheatersNearbyUseCase(ApiService apiService) {
    return new GetTheatersNearbyUseCase(apiService);
  }
}
