package com.thiennguyen.data.network.model;

import com.google.gson.annotations.SerializedName;

import com.thiennguyen.domain.model.GeometryModel;
import com.thiennguyen.domain.model.LocationModel;

/**
 * Created by thien.nguyen on 10/22/18.
 */

public class ApiGeometryModel implements GeometryModel {

  @SerializedName("location")
  private ApiLocationModel location;

  @Override
  public LocationModel getLocation() {
    return location;
  }

}
