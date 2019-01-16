package com.thiennguyen.filmseeker.ui.base;

import com.thiennguyen.filmseeker.BuildConfig;
import com.thiennguyen.filmseeker.R;
import com.thiennguyen.filmseeker.listener.OnDialogBtnClickListener;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public abstract class MvpActivity<P extends Presenter> extends BaseActivity implements BaseView {

  protected P presenter;

  @Override
  public AlertDialog onCreateAlertDialog(@StringRes int title, @StringRes int message, @StringRes int btnNegative,
      @StringRes int btnPositive, OnDialogBtnClickListener dialogBtnClickListener) {
    return new AlertDialog.Builder(this)
        .setCancelable(false)
        .setTitle(getResources().getString(title))
        .setMessage(getResources().getString(message))
        .setNegativeButton(getResources().getString(btnNegative), (dialogInterface, i) -> {
          dialogBtnClickListener.onBtnDialogNegativeClick();
          dialogInterface.dismiss();
        })
        .setPositiveButton(getResources().getString(btnPositive), (dialogInterface, i) -> {
          dialogBtnClickListener.onBtnDialogPositiveClick();
          dialogInterface.dismiss();
        }).create();
  }

  @Override
  public void onShowAppSetting() {
    startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)));
  }

  @Override
  public void onShowErrorMsg() {
    Toast.makeText(this, R.string.txt_error, Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    buildComponent();
    presenter = getPresenter();
    if (presenter == null) {
      throw new IllegalArgumentException("getPresenter() should not be NULL");
    }
    findViewById();
  }

  protected abstract void buildComponent();

  protected abstract void findViewById();

  protected abstract P getPresenter();
}
