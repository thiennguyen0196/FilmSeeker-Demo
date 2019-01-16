package com.thiennguyen.data.local.database;

import com.thiennguyen.data.local.model.DatabaseMovieModel;
import com.thiennguyen.data.local.model.DatabaseMovieModel_Table;
import com.thiennguyen.data.mapper.DatabaseMovieMapper;
import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.domain.model.MovieType;
import com.thiennguyen.domain.service.DatabaseService;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public class DatabaseServiceImpl implements DatabaseService {

  private final DatabaseMovieMapper mDatabaseMovieMapper;

  private final PublishSubject<MovieModel> mMovieSubject;

  public DatabaseServiceImpl(DatabaseMovieMapper databaseMovieMapper) {
    mDatabaseMovieMapper = databaseMovieMapper;
    mMovieSubject = PublishSubject.create();
  }

  @Override
  public Observable<Void> deleteMoviesTrending() {
    return Observable.defer(() -> {
      SQLite.delete(DatabaseMovieModel.class)
          .where(DatabaseMovieModel_Table.type.is(MovieType.TRENDING.getValue()))
          .execute();
      return Observable.just(null);
    });
  }

  @Override
  public Observable<Void> deleteMoviesTopRated() {
    return Observable.defer(() -> {
      SQLite.delete(DatabaseMovieModel.class)
          .where(DatabaseMovieModel_Table.type.is(MovieType.TOP_RATED.getValue()))
          .execute();
      return Observable.just(null);
    });
  }

  @Override
  public Observable<List<? extends MovieModel>> getMoviesTrending() {
    return Observable.defer(() -> {
      List<? extends MovieModel> movies = SQLite.select()
          .from(DatabaseMovieModel.class)
          .where(DatabaseMovieModel_Table.type.is(MovieType.TRENDING.getValue()))
          .queryList();
      return Observable.just(movies);
    });
  }

  @Override
  public Observable<List<? extends MovieModel>> getMoviesTopRated() {
    return Observable.defer(() -> {
      List<? extends MovieModel> movies = SQLite.select()
          .from(DatabaseMovieModel.class)
          .where(DatabaseMovieModel_Table.type.is(MovieType.TOP_RATED.getValue()))
          .orderBy(DatabaseMovieModel_Table.voteAverage, false)
          .queryList();
      return Observable.just(movies);
    });
  }

  @Override
  public Observable<Void> saveMovies(List<? extends MovieModel> movies) {
    return Observable.create(subscriber -> {
      try {
        List<DatabaseMovieModel> DatabaseMovies;
        if (movies != null) {
          DatabaseMovies = new ArrayList<>(movies.size());
        } else {
          DatabaseMovies = new ArrayList<>();
        }
        FastStoreModelTransaction.Builder<DatabaseMovieModel> builder;
        builder = FastStoreModelTransaction.saveBuilder(FlowManager.getModelAdapter(DatabaseMovieModel.class));
        if (movies != null) {
          for (MovieModel movie : movies) {
            DatabaseMovieModel DatabaseMovie = mDatabaseMovieMapper.convert(movie);
            DatabaseMovies.add(DatabaseMovie);
            builder.add(DatabaseMovie);
          }
        }
        FlowManager.getDatabase(AppDatabase.class)
            .executeTransaction(builder.build());
        for (DatabaseMovieModel DatabaseMovie : DatabaseMovies) {
          mMovieSubject.onNext(DatabaseMovie);
        }
        subscriber.onNext(null);
        subscriber.onComplete();
      } catch (Exception e) {
        if (subscriber != null && !subscriber.isDisposed()) {
          subscriber.onError(e);
        }
      }
    });
  }
}
