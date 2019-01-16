package com.thiennguyen.filmseeker.ui.main;

import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.R;
import com.thiennguyen.filmseeker.listener.OnDialogBtnClickListener;
import com.thiennguyen.filmseeker.ui.base.MvpActivity;
import com.thiennguyen.filmseeker.ui.main.di.MainComponent;
import com.thiennguyen.filmseeker.ui.main.di.MainModule;
import com.thiennguyen.filmseeker.ui.nearby_theaters.NearbyTheatersFragment;
import com.thiennguyen.filmseeker.ui.top_rated.TopRatedFragment;
import com.thiennguyen.filmseeker.ui.trending.TrendingFragment;
import com.thiennguyen.filmseeker.utils.PermissionUtils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView, OnDialogBtnClickListener {

  private static final String TAG = MainActivity.class.getSimpleName();

  private MainComponent mMainComponent;

  private NearbyTheatersFragment mNearbyTheatersFragment;

  private TopRatedFragment mTopRatedFragment;

  private TrendingFragment mTrendingFragment;

  private Fragment mPreviousFragment;

  private Fragment mSelectedFragment;

  private String mFragmentTag;

  private BottomNavigationView vBottomNavigation;

  private Toolbar vToolbar;

  private AlertDialog vExplainDeniedPermissionPermanentlyDialog;

  @Override
  public void onBtnDialogNegativeClick() {
    Toast.makeText(this, R.string.txt_location_permission_denied_msg, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onBtnDialogPositiveClick() {
    onShowAppSetting();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case Constant.Permission.MY_PERMISSION_LOCATION_REQUEST_CODE:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          mNearbyTheatersFragment.onPermissionGranted();
        } else if (PermissionUtils.checkShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
          Toast.makeText(this, R.string.txt_location_permission_denied_msg, Toast.LENGTH_SHORT).show();
        } else {
          vExplainDeniedPermissionPermanentlyDialog.show();
        }
    }
  }

  @Override
  protected void buildComponent() {
    Log.v(TAG, "buildComponent");
    mMainComponent = getActivityComponent().plus(new MainModule(this));
    mMainComponent.inject(this);
  }

  @Override
  protected void findViewById() {
    vBottomNavigation = findViewById(R.id.bottom_navigation);
    vToolbar = findViewById(R.id.toolbar);
  }

  @Override
  protected int getLayoutResID() {
    return R.layout.activity_main;
  }

  @Override
  protected MainPresenter getPresenter() {
    return mMainComponent.getMainPresenter();
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    SystemClock.sleep(Constant.Splash.DELAY_TIME);
    setTheme(R.style.AppTheme_Light);
    super.onCreate(savedInstanceState);

    setSupportActionBar(vToolbar);
    vToolbar.setTitleTextColor(getResources().getColor(R.color.mdTextColorPrimary_dark));
    vExplainDeniedPermissionPermanentlyDialog = onCreateAlertDialog(R.string.dialog_permission_denied_permanently_location_title,
        R.string.dialog_permission_denied_permanently_location_msg, R.string.dialog_btn_not_now, R.string.dialog_btn_app_setting, this);

    // Handle bottom navigation view
    mNearbyTheatersFragment = NearbyTheatersFragment.newInstance();
    mTopRatedFragment = TopRatedFragment.newInstance();
    mTrendingFragment = TrendingFragment.newInstance();
    mSelectedFragment = mTrendingFragment;
    mPreviousFragment = mTrendingFragment;
    mFragmentTag = TrendingFragment.class.getSimpleName();
    getSupportActionBar().setTitle(R.string.title_trending_movies);
    switchFragment();
    vBottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
      mPreviousFragment = getSupportFragmentManager().findFragmentByTag(mFragmentTag);
      switch (menuItem.getItemId()) {
        case R.id.action_trending_movies:
          mSelectedFragment = mTrendingFragment;
          mFragmentTag = TrendingFragment.class.getSimpleName();
          vToolbar.setTitle(R.string.title_trending_movies);
          break;
        case R.id.action_top_rated:
          mSelectedFragment = mTopRatedFragment;
          mFragmentTag = TopRatedFragment.class.getSimpleName();
          vToolbar.setTitle(R.string.title_top_rated);
          break;
        case R.id.action_nearby_theaters:
          mSelectedFragment = mNearbyTheatersFragment;
          mFragmentTag = NearbyTheatersFragment.class.getSimpleName();
          vToolbar.setTitle(R.string.title_nearby_theaters);
          break;
      }
      switchFragment();
      return true;
    });
  }

  private void switchFragment() {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();

    // if the fragment has not yet been added to the container, add it first
    if (fragmentManager.findFragmentByTag(mFragmentTag) == null) {
      transaction.add(R.id.fragment_holder, mSelectedFragment, mFragmentTag);
    }

    transaction.hide(mPreviousFragment)
        .show(mSelectedFragment)
        .commit();
  }
}
