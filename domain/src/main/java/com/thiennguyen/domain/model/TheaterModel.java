package com.thiennguyen.domain.model;

import android.os.Parcelable;

import java.util.List;

/**
 * Created by thien.nguyen on 10/22/18.
 */

public interface TheaterModel extends Parcelable {

  String getId();

  GeometryModel getGeometry();

  String getName();

  String getPlaceId();

  double getRating();

  List<? extends TheaterPhotoModel> getTheaterPhotos();

  String getVicinity();
}
