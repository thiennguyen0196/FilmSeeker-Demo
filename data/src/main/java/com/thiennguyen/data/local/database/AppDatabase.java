package com.thiennguyen.data.local.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by thien.nguyen on 10/11/18.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

  public static final String NAME = "FilmSeekerDB";

  public static final int VERSION = 1;

}
