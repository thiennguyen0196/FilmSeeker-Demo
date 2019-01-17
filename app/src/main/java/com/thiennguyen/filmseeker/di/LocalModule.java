package com.thiennguyen.filmseeker.di;

import com.thiennguyen.data.local.database.DatabaseServiceImpl;
import com.thiennguyen.data.mapper.DatabaseMovieMapper;
import com.thiennguyen.domain.service.DatabaseService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by thien.nguyen on 10/11/18.
 */

@Module
public class LocalModule {

  @Provides
  @Singleton
  public DatabaseMovieMapper provideDatabaseMovieMapper() {
    return new DatabaseMovieMapper();
  }

  @Provides
  @Singleton
  public DatabaseService provideDatabaseService(DatabaseMovieMapper databaseMovieMapper) {
    return new DatabaseServiceImpl(databaseMovieMapper);
  }}
