package com.thiennguyen.filmseeker.ui.base;

import com.thiennguyen.filmseeker.listener.OnDialogBtnClickListener;

import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public interface BaseView {

  AlertDialog onCreateAlertDialog(@StringRes int title, @StringRes int message, @StringRes int btnNegative, @StringRes int btnPositive,
      OnDialogBtnClickListener dialogBtnClickListener);

  void onShowAppSetting();

  void onShowErrorMsg();
}
