package com.thiennguyen.data.mapper;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public abstract class BaseMapper<F, T> {

  public abstract T convert(F from);
}

