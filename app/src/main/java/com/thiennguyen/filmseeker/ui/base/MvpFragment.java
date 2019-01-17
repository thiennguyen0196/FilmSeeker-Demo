package com.thiennguyen.filmseeker.ui.base;

import com.thiennguyen.filmseeker.BuildConfig;
import com.thiennguyen.filmseeker.R;
import com.thiennguyen.filmseeker.listener.OnDialogBtnClickListener;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by thien.nguyen on 10/11/18.
 */

public abstract class MvpFragment<P extends Presenter> extends Fragment implements BaseView {

  protected P presenter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(getLayoutResID(), container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    findViewById(view);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    buildComponent();
    presenter = getPresenter();
    if (presenter == null) {
      throw new IllegalArgumentException("getPresenter() should not be NULL");
    }
  }

  @Override
  public void onDetach() {
    getPresenter().detachView();
    super.onDetach();
  }

  @Override
  public AlertDialog onCreateAlertDialog(@StringRes int title, @StringRes int message, @StringRes int btnNegative,
      @StringRes int btnPositive, OnDialogBtnClickListener dialogBtnClickListener) {
    return new AlertDialog.Builder(getActivity())
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
    Toast.makeText(getActivity(), R.string.txt_error, Toast.LENGTH_SHORT).show();
  }

  protected abstract void buildComponent();

  protected abstract void findViewById(View v);

  @LayoutRes
  protected abstract int getLayoutResID();

  protected abstract P getPresenter();
}

