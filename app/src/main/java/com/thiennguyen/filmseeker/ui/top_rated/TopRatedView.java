package com.thiennguyen.filmseeker.ui.top_rated;

import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.filmseeker.ui.base.BaseView;

import java.util.List;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public interface TopRatedView extends BaseView {

  void onAddItemNull();

  void onDisableAdapterLoading();

  void onDisableRefreshLayout();

  void onMoviesTopRatedLoaded(List<? extends MovieModel> movies);

  void onMoviesTopRatedRefresh(List<? extends MovieModel> movies);

  void onShowErrorLayout(boolean isVisible);

  void onShowProgressBar(boolean isShowProgressBar);

  void onRemoveItemNull();
}
