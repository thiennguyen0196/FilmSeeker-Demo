package com.thiennguyen.filmseeker.ui;

import com.thiennguyen.filmseeker.R;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public enum MovieLayout {

  VERTICAL(R.layout.item_movie_vertical),
  STAGGERED(R.layout.item_movie_grid);

  private int value;

  MovieLayout(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
