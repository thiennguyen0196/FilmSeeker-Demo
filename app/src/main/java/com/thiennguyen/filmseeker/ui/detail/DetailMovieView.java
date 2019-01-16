package com.thiennguyen.filmseeker.ui.detail;

import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.filmseeker.ui.base.BaseView;

import java.util.List;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public interface DetailMovieView extends BaseView {

  void onSimilarMoviesLoaded(List<? extends MovieModel> movies);
}
