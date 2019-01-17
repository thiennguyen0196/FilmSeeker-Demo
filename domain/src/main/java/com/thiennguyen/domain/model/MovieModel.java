package com.thiennguyen.domain.model;

import android.os.Parcelable;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public interface MovieModel extends Parcelable {

  long getId();

  String getBackdropPath();

  String getOverview();

  String getPosterPath();

  String getReleaseDate();

  String getTitle();

  int getType();

  float getVoteAverage();

  void setType(MovieType type);
}
