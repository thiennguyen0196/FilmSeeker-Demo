package com.thiennguyen.filmseeker.ui.detail;

import com.facebook.drawee.view.SimpleDraweeView;
import com.thiennguyen.domain.model.MovieModel;
import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.R;
import com.thiennguyen.filmseeker.listener.OnItemClickListener;
import com.thiennguyen.filmseeker.adapter.MovieSimilarAdapter;
import com.thiennguyen.filmseeker.ui.base.MvpActivity;
import com.thiennguyen.filmseeker.ui.detail.di.DetailMovieComponent;
import com.thiennguyen.filmseeker.ui.detail.di.DetailMovieModule;
import com.thiennguyen.filmseeker.utils.DateUtils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

/**
 * Created by thien.nguyen on 10/12/18.
 */

public class DetailMovieActivity extends MvpActivity<DetailMoviePresenter> implements DetailMovieView, OnItemClickListener<MovieModel> {

  private static final String TAG = DetailMovieActivity.class.getSimpleName();

  private DetailMovieComponent mDetailMovieComponent;

  private MovieModel mMovie;

  private MovieSimilarAdapter mMovieSimilarAdapter;

  private AppBarLayout vAppBarLayout;

  private CollapsingToolbarLayout vCollapsingToolbarLayout;

  private RecyclerView vListSimilarMovies;

  private SimpleDraweeView vImgBackdrop;

  private SimpleDraweeView vImgViewPoster;

  private TextView vTxtOverview;

  private TextView vTxtReleaseDate;

  private TextView vTxtTitle;

  private TextView vTxtVoteAverage;

  private Toolbar vToolbar;

  public static Intent newIntent(Activity activity, MovieModel movie) {
    Intent intent = new Intent(activity, DetailMovieActivity.class);
    intent.putExtra(Constant.Extra.MOVIE, movie);
    return intent;
  }

  @Override
  public void onItemClick(MovieModel movie) {
    startActivity(newIntent(this, movie));
    finish();
  }

  @Override
  public void onSimilarMoviesLoaded(List<? extends MovieModel> similarMovies) {
    mMovieSimilarAdapter.setMovies(similarMovies);
  }

  @Override
  protected void buildComponent() {
    Log.v(TAG, "buildComponent");
    mDetailMovieComponent = getActivityComponent().plus(new DetailMovieModule(this));
    mDetailMovieComponent.inject(this);
  }

  @Override
  protected int getLayoutResID() {
    return R.layout.activity_detail_movie;
  }

  @Override
  protected DetailMoviePresenter getPresenter() {
    return mDetailMovieComponent.getDetailMoviePresenter();
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mMovieSimilarAdapter = new MovieSimilarAdapter(this);
    setSupportActionBar(vToolbar);
    vToolbar.setNavigationOnClickListener(v -> finish());
    vListSimilarMovies.setAdapter(mMovieSimilarAdapter);
    vListSimilarMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    vListSimilarMovies.setItemAnimator(null);
    mMovie = getIntent().getParcelableExtra(Constant.Extra.MOVIE);
    setMovie(mMovie);
    onAppBarChangeListener();
  }

  @Override
  protected void findViewById() {
    vAppBarLayout = findViewById(R.id.appBarLayout);
    vCollapsingToolbarLayout = findViewById(R.id.collapsingToolBarLayout);
    vListSimilarMovies = findViewById(R.id.listSimilarMovies);
    vImgBackdrop = findViewById(R.id.imgBackdrop);
    vImgViewPoster = findViewById(R.id.imgPoster);
    vTxtOverview = findViewById(R.id.txtOverview);
    vTxtReleaseDate = findViewById(R.id.txtReleaseDate);
    vTxtTitle = findViewById(R.id.txtTitle);
    vTxtVoteAverage = findViewById(R.id.txtVoteAverage);
    vToolbar = findViewById(R.id.toolbar);
  }

  private void setMovie(MovieModel movie) {
    if (movie == null) {
      Log.e(TAG, "movieModel >>>>>>>>>> NULL");
      return;
    }
    mMovie = movie;
    presenter.loadBySimilarMovie(movie.getId());
    vCollapsingToolbarLayout.setTitle(movie.getTitle());
    vTxtTitle.setText(movie.getTitle());
    vTxtOverview.setText(movie.getOverview());
    vTxtReleaseDate.setText(String.format(getResources().getString(R.string.txt_released_date),
        DateUtils.formatDateType(Constant.DateTime.DATE_INPUT_FORMAT, Constant.DateTime.DATE_OUTPUT_FORMAT, movie.getReleaseDate())));
    vTxtVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
    String pathPoster = String.format(Constant.Movie.URL_POSTER, movie.getPosterPath());
    String pathBackdrop = String.format(Constant.Movie.URL_BACKDROP, movie.getBackdropPath());
    Uri pathPosterUri = Uri.parse(pathPoster);
    Uri pathBackdropUri = Uri.parse(pathBackdrop);
    if (pathPosterUri != Uri.EMPTY) {
      vImgViewPoster.setImageURI(pathPosterUri);
    } else {
      vImgViewPoster.setImageURI("");
    }
    if (pathBackdropUri != Uri.EMPTY) {
      vImgBackdrop.setImageURI(pathBackdropUri);
    } else {
      vImgBackdrop.setImageURI("");
    }
  }

  private void onAppBarChangeListener() {
    vAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
      if (getSupportActionBar() != null) {
        if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
          //  Collapsed
          getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.background_app_gradient));
        } else {
          //Expanded
          getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_blank_transparent));
        }
      }
    });
  }
}
