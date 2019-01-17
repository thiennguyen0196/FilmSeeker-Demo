package com.thiennguyen.filmseeker.ui.nearby_theaters;

import com.thiennguyen.domain.model.TheaterModel;
import com.thiennguyen.filmseeker.ui.base.BaseView;

import java.util.List;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public interface NearbyTheatersView extends BaseView {

  void onShowProgressDialog(boolean isShowProgressDialog);

  void onTheaterLoaded(List<? extends TheaterModel> theaters);
}
