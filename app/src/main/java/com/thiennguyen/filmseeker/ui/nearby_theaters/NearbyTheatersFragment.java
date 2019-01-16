package com.thiennguyen.filmseeker.ui.nearby_theaters;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.thiennguyen.domain.model.TheaterModel;
import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.R;
import com.thiennguyen.filmseeker.adapter.TheaterAdapter;
import com.thiennguyen.filmseeker.listener.OnDialogBtnClickListener;
import com.thiennguyen.filmseeker.listener.OnItemClickListener;
import com.thiennguyen.filmseeker.listener.OnPermissionCheckListener;
import com.thiennguyen.filmseeker.ui.base.BaseActivity;
import com.thiennguyen.filmseeker.ui.base.MvpFragment;
import com.thiennguyen.filmseeker.ui.base.di.ActivityComponent;
import com.thiennguyen.filmseeker.ui.nearby_theaters.di.NearbyTheatersComponent;
import com.thiennguyen.filmseeker.ui.nearby_theaters.di.NearbyTheatersModule;
import com.thiennguyen.filmseeker.utils.PermissionUtils;

import android.Manifest;
import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public class NearbyTheatersFragment extends MvpFragment<NearbyTheatersPresenter> implements NearbyTheatersView, OnMapReadyCallback,
    OnItemClickListener<TheaterModel>, OnPermissionCheckListener, OnDialogBtnClickListener {

  private static final String TAG = NearbyTheatersFragment.class.getSimpleName();

  private static ProgressDialog vProgressDialog;

  private NearbyTheatersComponent mNearbyTheatersComponent;

  private GoogleMap mGoogleMap;

  private FusedLocationProviderClient mFusedLocationProviderClient;

  private Location mCurLocation;

  private LocationCallback mLocationCallback;

  private LocationRequest mLocationRequest;

  private Marker mTheaterMarker;

  private TheaterAdapter mTheaterAdapter;

  private AlertDialog vExplainPermissionDialog;

  private FloatingActionButton vBtnReload;

  private MapView vMap;

  private RecyclerView vListTheaters;

  private RelativeLayout.LayoutParams vBtnReloadParams;

  public static NearbyTheatersFragment newInstance() {
    return new NearbyTheatersFragment();
  }

  @Override
  public void onBtnDialogNegativeClick() {
    Toast.makeText(getContext(), R.string.txt_location_permission_denied_msg, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onBtnDialogPositiveClick() {
    PermissionUtils.requestPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION,
        Constant.Permission.MY_PERMISSION_LOCATION_REQUEST_CODE);
  }

  @Override
  public void onItemClick(TheaterModel theater) {
    if (mTheaterMarker != null) {
      mTheaterMarker.remove();
    }
    LatLng latLng = new LatLng(theater.getGeometry().getLocation().getLat(), theater.getGeometry().getLocation().getLng());
    MarkerOptions markerOptions = new MarkerOptions();
    markerOptions.position(latLng);
    markerOptions.title(theater.getVicinity());
    mTheaterMarker = mGoogleMap.addMarker(markerOptions);
    mGoogleMap.moveCamera(
        CameraUpdateFactory.newLatLngZoom(latLng, Constant.Map.ZOOM));
  }

  @Override
  public void onPermissionGranted() {
    mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    mGoogleMap.setMyLocationEnabled(true);
  }

  @Override
  public void onPermissionShowRationale() {
    vExplainPermissionDialog.show();
  }

  @Override
  public void onShowProgressDialog(boolean isShowProgressDialog) {
    if (isShowProgressDialog) {
      vProgressDialog.setCancelable(false);
      vProgressDialog.setMessage(getResources().getString(R.string.dialog_msg_loading));
      vProgressDialog.show();
    } else {
      vProgressDialog.dismiss();
    }
  }

  @Override
  public void onTheaterLoaded(List<? extends TheaterModel> theaters) {
    if (theaters.size() > 0) {
      vListTheaters.setVisibility(View.VISIBLE);
      vBtnReloadParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
      vBtnReloadParams.addRule(RelativeLayout.ABOVE, R.id.listTheaters);
    } else {
      vListTheaters.setVisibility(View.GONE);
      vBtnReloadParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
      vBtnReloadParams.addRule(RelativeLayout.ABOVE, 0);
    }
    mTheaterAdapter.setTheaters(theaters);
    vBtnReload.setLayoutParams(vBtnReloadParams);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    vProgressDialog = new ProgressDialog(getContext());
    mTheaterAdapter = new TheaterAdapter(this);
    vListTheaters.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    vListTheaters.setItemAnimator(null);
    vListTheaters.setAdapter(mTheaterAdapter);
    vBtnReload.setOnClickListener(v -> PermissionUtils
        .checkPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION, Constant.Permission.MY_PERMISSION_LOCATION_REQUEST_CODE,
            this));
    vExplainPermissionDialog = onCreateAlertDialog(R.string.dialog_permission_denied_location_title,
        R.string.dialog_permission_denied_location_msg, R.string.dialog_btn_deny, R.string.dialog_btn_allow, this);
    mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
    vMap.onCreate(savedInstanceState);
    vMap.getMapAsync(this);
    mLocationCallback = provideLocationCallBack();
  }

  @Override
  public void onResume() {
    super.onResume();
    vMap.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
    vMap.onPause();
    if (mFusedLocationProviderClient != null) {
      mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    vMap.onDestroy();
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    vMap.onLowMemory();
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    mGoogleMap = googleMap;
    mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
    mLocationRequest = new LocationRequest();

    PermissionUtils
        .checkPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION, Constant.Permission.MY_PERMISSION_LOCATION_REQUEST_CODE,
            this);
  }

  @Override
  protected void buildComponent() {
    Log.v(TAG, "buildComponent");
    ActivityComponent activityComponent = ((BaseActivity) getActivity()).getActivityComponent();
    if (activityComponent != null) {
      mNearbyTheatersComponent = activityComponent.plus(new NearbyTheatersModule(this));
      mNearbyTheatersComponent.inject(this);
    }
  }

  @Override
  protected void findViewById(View view) {
    vBtnReload = view.findViewById(R.id.btnReload);
    vMap = view.findViewById(R.id.map);
    vListTheaters = view.findViewById(R.id.listTheaters);
    vBtnReloadParams = (RelativeLayout.LayoutParams) vBtnReload.getLayoutParams();
  }

  @Override
  protected NearbyTheatersPresenter getPresenter() {
    return mNearbyTheatersComponent.getNearbyTheatersPresenter();
  }

  @Override
  protected int getLayoutResID() {
    return R.layout.fragment_nearby_theaters;
  }

  private LocationCallback provideLocationCallBack() {
    return new LocationCallback() {
      @Override
      public void onLocationResult(LocationResult locationResult) {
        List<Location> locationList = locationResult.getLocations();
        if (locationList.size() > 0) {
          //The last location in the list is the newest
          Location location = locationList.get(locationList.size() - 1);
          Log.v(TAG, String.format("Current Location: %s %s", location.getLatitude(), location.getLongitude()));
          mCurLocation = location;
          if (mTheaterMarker != null) {
            mTheaterMarker.remove();
          }
          presenter.loadNearbyTheaters(mCurLocation);

          // Move map camera
          mGoogleMap.moveCamera(
              CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), Constant.Map.ZOOM));
        }
      }
    };
  }
}
