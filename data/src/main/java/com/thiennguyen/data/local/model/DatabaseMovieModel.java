package com.thiennguyen.data.local.model;

import com.thiennguyen.data.local.database.AppDatabase;
import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.domain.model.MovieType;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thien.nguyen on 10/11/18.
 */

@Table(database = AppDatabase.class)
public class DatabaseMovieModel extends BaseModel implements MovieModel {

  @PrimaryKey
  long id;

  @Column
  String backdropPath;

  @Column
  String overview;

  @Column
  String posterPath;

  @Column
  String releaseDate;

  @Column
  String title;

  @Column
  int type;

  @Column
  float voteAverage;

  public DatabaseMovieModel() {
  }

  public DatabaseMovieModel(long id, String backdropPath, String overview, String posterPath, String releaseDate, String title,
      int type, float voteAverage) {
    this.id = id;
    this.backdropPath = backdropPath;
    this.overview = overview;
    this.posterPath = posterPath;
    this.releaseDate = releaseDate;
    this.title = title;
    this.type = type;
    this.voteAverage = voteAverage;
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

  protected DatabaseMovieModel(Parcel in) {
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
  public static final Creator<DatabaseMovieModel> CREATOR = new Creator<DatabaseMovieModel>() {
    @Override
    public DatabaseMovieModel createFromParcel(Parcel in) {
      return new DatabaseMovieModel(in);
    }

    @Override
    public DatabaseMovieModel[] newArray(int size) {
      return new DatabaseMovieModel[size];
    }
  };
}
