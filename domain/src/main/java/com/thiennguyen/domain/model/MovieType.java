package com.thiennguyen.domain.model;

/**
 * Created by thien.nguyen on 10/13/18.
 */

public enum MovieType {

  TRENDING(1),
  TOP_RATED(2);

  private int value;

  MovieType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
