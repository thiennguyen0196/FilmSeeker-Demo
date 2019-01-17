package com.thiennguyen.filmseeker;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public class Constant {

  public interface DateTime {

    String DATE_INPUT_FORMAT = "yyyy-MM-dd";
    String DATE_OUTPUT_FORMAT = "MMM dd, yyyy";
  }

  public interface Extra {

    String MOVIE = "MOVIE";
  }

  public interface Map {

    String API_KEY_NAME = "key";
    String LANGUAGE = "en";
    String LOCATION_TYPE = "movie_theater";
    String LOCATION_RANK_BY = "distance";
    String MAP_URL = "https://maps.googleapis.com/maps/api/";
    String URL_THEATER = "https://maps.googleapis.com/maps/api/place/photo?&photoreference=%s&maxwidth=300&key=%s";
    float ZOOM = 14f;
  }

  public interface Movie {

    String API_KEY_NAME = "api_key";
    String LANGUAGE = "en-US";
    String MOVIE_URL = "https://api.themoviedb.org/";
    int STAGGERED_SPAN_COUNT = 2;
    int STAGGERED_ORIENTATION = 1;
    String URL_BACKDROP = "https://image.tmdb.org/t/p/original%s";
    String URL_POSTER = "https://image.tmdb.org/t/p/w600_and_h900_bestv2%s";
    int PAGE_1 = 1;
  }

  public interface Network {

    int TIME_OUT = 30;
  }

  public interface Permission {

    int MY_PERMISSION_LOCATION_REQUEST_CODE = 123;
  }

  public interface Splash {

    int DELAY_TIME = 500;
  }

  public interface ViewType {

    int ITEM = 0;
    int LOADING = 1;
  }
}
