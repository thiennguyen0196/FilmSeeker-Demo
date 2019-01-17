package com.thiennguyen.filmseeker.utils;

import com.thiennguyen.filmseeker.listener.OnPermissionCheckListener;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by thien.nguyen on 10/15/18.
 */

public final class PermissionUtils {

  public static void checkPermission(Activity activity, String permission, int permissionCode,
      OnPermissionCheckListener permissionCheckListener) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
        // For API >= 23
        // If permission is already granted, do task
        permissionCheckListener.onPermissionGranted();

      } else if (checkShowRequestPermissionRationale(activity, permission)) {
        // If users deny permission but not choose 'Don't ask again' rationale,
        // show action to explain the permission, then request again
        permissionCheckListener.onPermissionShowRationale();

      } else {
        // Ask for permission for 1st time
        requestPermission(activity, permission, permissionCode);

      }
    } else {
      // For API < 23, just do task
      permissionCheckListener.onPermissionGranted();

    }
  }

  public static boolean checkShowRequestPermissionRationale(Activity activity, String permission) {
    return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
  }

  public static void requestPermission(Activity activity, String permission, int permissionCode) {
    ActivityCompat.requestPermissions(activity, new String[]{permission}, permissionCode);
  }

}
