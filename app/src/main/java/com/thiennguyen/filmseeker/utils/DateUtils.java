package com.thiennguyen.filmseeker.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public final class DateUtils {

  private static final String TAG = DateUtils.class.getSimpleName();

  public static String formatDateType(String inputFormat, String outputFormat, String inputDate) {
    if (inputFormat == null || outputFormat == null || inputDate == null) {
      return null;
    }
    String outputDate = "";

    SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, Locale.getDefault());
    SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, Locale.getDefault());

    try {
      outputDate = df_output.format(df_input.parse(inputDate));
    } catch (ParseException e) {
      Log.e(TAG, "ParseException - dateFormat");
    }
    return outputDate;
  }
}
