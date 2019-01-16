package com.thiennguyen.data.mapper;

import com.thiennguyen.data.local.model.DatabaseMovieModel;
import com.thiennguyen.domain.model.MovieModel;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public class DatabaseMovieMapper extends BaseMapper<MovieModel, DatabaseMovieModel> {

  @Override
  public DatabaseMovieModel convert(MovieModel from) {
    if (from == null) {
      return null;
    }
    return from instanceof DatabaseMovieModel ? (DatabaseMovieModel) from
        : new DatabaseMovieModel(from.getId(), from.getBackdropPath(), from.getOverview(), from.getPosterPath(), from.getReleaseDate(),
            from.getTitle(), from.getType(), from.getVoteAverage());
  }
}
