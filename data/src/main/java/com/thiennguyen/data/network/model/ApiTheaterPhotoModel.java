package com.thiennguyen.data.network.model;

import com.google.gson.annotations.SerializedName;

import com.thiennguyen.domain.model.TheaterPhotoModel;

/**
 * Created by thien.nguyen on 10/22/18.
 */

public class ApiTheaterPhotoModel implements TheaterPhotoModel {

  @SerializedName("height")
  private int height;

  @SerializedName("photo_reference")
  private String photoReference;

  @SerializedName("width")
  private int width;

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public String getPhotoRef() {
    return photoReference;
  }

  @Override
  public int getWidth() {
    return width;
  }

}
