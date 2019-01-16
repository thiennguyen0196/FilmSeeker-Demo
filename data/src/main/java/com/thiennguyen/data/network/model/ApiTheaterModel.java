package com.thiennguyen.data.network.model;

import com.google.gson.annotations.SerializedName;

import com.thiennguyen.domain.model.TheaterModel;
import com.thiennguyen.domain.model.TheaterPhotoModel;
import com.thiennguyen.domain.model.GeometryModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thien.nguyen on 10/22/18.
 */

public class ApiTheaterModel implements TheaterModel {

  @SerializedName("id")
  private String id;

  @SerializedName("geometry")
  private ApiGeometryModel geometry;

  @SerializedName("name")
  private String name;

  @SerializedName("place_id")
  private String placeId;

  @SerializedName("photos")
  private List<ApiTheaterPhotoModel> photos;

  @SerializedName("rating")
  private double rating;

  @SerializedName("vicinity")
  private String vicinity;

  @Override
  public String getId() {
    return id;
  }

  @Override
  public GeometryModel getGeometry() {
    return geometry;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getPlaceId() {
    return placeId;
  }

  @Override
  public double getRating() {
    return rating;
  }

  @Override
  public List<? extends TheaterPhotoModel> getTheaterPhotos() {
    return photos;
  }

  @Override
  public String getVicinity() {
    return vicinity;
  }

  protected ApiTheaterModel(Parcel in) {
    id = in.readString();
    geometry = (ApiGeometryModel) in.readValue(ApiGeometryModel.class.getClassLoader());
    name = in.readString();
    placeId = in.readString();
    if (in.readByte() == 0x01) {
      photos = new ArrayList<ApiTheaterPhotoModel>();
      in.readList(photos, ApiTheaterPhotoModel.class.getClassLoader());
    } else {
      photos = null;
    }
    rating = in.readDouble();
    vicinity = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeValue(geometry);
    dest.writeString(name);
    dest.writeString(placeId);
    if (photos == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeList(photos);
    }
    dest.writeDouble(rating);
    dest.writeString(vicinity);
  }

  @SuppressWarnings("unused")
  public static final Creator<ApiTheaterModel> CREATOR = new Creator<ApiTheaterModel>() {
    @Override
    public ApiTheaterModel createFromParcel(Parcel in) {
      return new ApiTheaterModel(in);
    }

    @Override
    public ApiTheaterModel[] newArray(int size) {
      return new ApiTheaterModel[size];
    }
  };

}
