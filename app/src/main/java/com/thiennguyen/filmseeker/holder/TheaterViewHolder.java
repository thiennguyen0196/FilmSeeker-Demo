package com.thiennguyen.filmseeker.holder;

import com.facebook.drawee.view.SimpleDraweeView;
import com.thiennguyen.domain.model.TheaterModel;
import com.thiennguyen.filmseeker.Constant;
import com.thiennguyen.filmseeker.R;
import com.thiennguyen.filmseeker.listener.OnItemClickListener;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by thien.nguyen on 1/14/19.
 */

public class TheaterViewHolder extends RecyclerView.ViewHolder {

  private static final String TAG = TheaterViewHolder.class.getSimpleName();

  private final String API_GOOGLE_MAP_KEY;

  private TheaterModel mTheater;

  private SimpleDraweeView vImgTheater;

  private TextView vTxtName;

  public TheaterViewHolder(View itemView, OnItemClickListener itemClickListener) {
    super(itemView);

    vImgTheater = itemView.findViewById(R.id.imgTheater);
    vTxtName = itemView.findViewById(R.id.txtName);
    this.API_GOOGLE_MAP_KEY = itemView.getResources().getString(R.string.api_google_map_key);
    itemView.setOnClickListener(v -> {
      if (itemClickListener != null) {
        itemClickListener.onItemClick(mTheater);
      }
    });
  }

  public void bind(TheaterModel theater) {
    if (theater == null) {
      Log.e(TAG, "THEATER MODEL >>>>>>>>>> NULL");
      return;
    }
    Log.i(TAG, String.valueOf(theater));
    mTheater = theater;
    vTxtName.setText(theater.getName());
    if (theater.getTheaterPhotos() == null || theater.getTheaterPhotos().isEmpty()) {
      return;
    }
    Uri pathImgTheater = Uri
        .parse(String.format(Constant.Map.URL_THEATER, theater.getTheaterPhotos().get(0).getPhotoRef(), this.API_GOOGLE_MAP_KEY));
    if (pathImgTheater != Uri.EMPTY) {
      vImgTheater.setImageURI(pathImgTheater);
    } else {
      vImgTheater.setImageURI("");
    }
  }
}
