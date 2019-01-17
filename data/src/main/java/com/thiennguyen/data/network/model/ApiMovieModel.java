package com.thiennguyen.data.network.model;

import com.google.gson.annotations.SerializedName;

import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.domain.model.MovieType;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public class ApiMovieModel implements MovieModel {

  @SerializedName("id")
  private long id;

  @SerializedName("backdrop_path")
  private String backdropPath;

  @SerializedName("overview")
  private String overview;

  @SerializedName("poster_path")
  private String posterPath;

  @SerializedName("release_date")
  private String releaseDate;

  @SerializedName("title")
  private String title;

  private int type;

  @SerializedName("vote_average")
  private float voteAverage;

  public ApiMovieModel() {
  }

  @Override
  public long getId() {
    return id;
  }

  @Override
  public String getBackdropPath() {
    return backdropPath;
  }

  @Override
  public String getOverview() {
    return overview;
  }

  @Override
  public String getPosterPath() {
    return posterPath;
  }

  @Override
  public String getReleaseDate() {
    return releaseDate;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public int getType() {
    return type;
  }

  @Override
  public float getVoteAverage() {
    return voteAverage;
  }

  @Override
  public void setType(MovieType type) {
    this.type = type.getValue();
  }

  protected ApiMovieModel(Parcel in) {
    id = in.readLong();
    backdropPath = in.readString();
    overview = in.readString();
    posterPath = in.readString();
    releaseDate = in.readString();
    title = in.readString();
    type = in.readInt();
    voteAverage = in.readFloat();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(id);
    dest.writeString(backdropPath);
    dest.writeString(overview);
    dest.writeString(posterPath);
    dest.writeString(releaseDate);
    dest.writeString(title);
    dest.writeInt(type);
    dest.writeFloat(voteAverage);
  }

  @SuppressWarnings("unused")
  public static final Creator<ApiMovieModel> CREATOR = new Creator<ApiMovieModel>() {
    @Override
    public ApiMovieModel createFromParcel(Parcel in) {
      return new ApiMovieModel(in);
    }

    @Override
    public ApiMovieModel[] newArray(int size) {
      return new ApiMovieModel[size];
    }
  };

}
