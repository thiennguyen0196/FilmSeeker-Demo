package com.thiennguyen.data.network.model;

import com.google.gson.annotations.SerializedName;

import com.thiennguyen.domain.model.LocationModel;

/**
 * Created by thien.nguyen on 10/22/18.
 */

public class ApiLocationModel implements LocationModel {

  @SerializedName("lat")
  private double lat;

  @SerializedName("lng")
  private double lng;

  @Override
  public double getLat() {
    return lat;
  }

  @Override
  public double getLng() {
    return lng;
  }

}
