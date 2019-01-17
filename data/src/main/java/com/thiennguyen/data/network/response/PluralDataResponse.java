package com.thiennguyen.data.network.response;

import java.util.List;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public class PluralDataResponse<T> {

  List<T> results;

  public List<T> getResults() {
    return results;
  }

}
