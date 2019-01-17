package com.thiennguyen.filmseeker.ui.trending;

import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.filmseeker.ui.base.BaseView;

import java.util.List;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public interface TrendingView extends BaseView {

  void onAddItemNull();

  void onDisableAdapterLoading();

  void onDisableRefreshLayout();

  void onMoviesTrendingLoaded(List<? extends MovieModel> movies);

  void onMoviesTrendingRefresh(List<? extends MovieModel> movies);

  void onShowErrorLayout(boolean isVisible);

  void onShowProgressBar(boolean isShowProgressBar);

  void onRemoveItemNull();
}
