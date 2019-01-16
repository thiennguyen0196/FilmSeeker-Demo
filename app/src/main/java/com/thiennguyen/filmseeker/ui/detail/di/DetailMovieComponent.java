package com.thiennguyen.filmseeker.ui.detail.di;

import com.thiennguyen.filmseeker.ui.detail.DetailMovieActivity;
import com.thiennguyen.filmseeker.ui.detail.DetailMoviePresenter;

import dagger.Subcomponent;

/**
 * Created by thien.nguyen on 10/12/18.
 */

@Subcomponent(modules = {DetailMovieModule.class})
public interface DetailMovieComponent {

  DetailMoviePresenter getDetailMoviePresenter();

  void inject(DetailMovieActivity detailMovieActivity);
}
