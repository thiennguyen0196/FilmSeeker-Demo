package com.thiennguyen.filmseeker.adapter;

import com.thiennguyen.domain.model.TheaterModel;
import com.thiennguyen.filmseeker.R;
import com.thiennguyen.filmseeker.holder.TheaterViewHolder;
import com.thiennguyen.filmseeker.listener.OnItemClickListener;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thien.nguyen on 10/22/18.
 */

public class TheaterAdapter extends RecyclerView.Adapter<TheaterViewHolder> {

  private final List<TheaterModel> mTheaters;

  private final OnItemClickListener mOnItemClickListener;

  public TheaterAdapter(OnItemClickListener itemClickListener) {
    mTheaters = new ArrayList<>();
    mOnItemClickListener = itemClickListener;
  }

  @Override
  public int getItemCount() {
    return mTheaters == null ? 0 : mTheaters.size();
  }

  @Override
  public void onBindViewHolder(TheaterViewHolder holder, int position) {
    holder.bind(mTheaters.get(position));
  }

  @Override
  public TheaterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    return new TheaterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_theater, viewGroup, false),
        mOnItemClickListener);
  }

  public void setTheaters(List<? extends TheaterModel> theaters) {
    mTheaters.clear();
    mTheaters.addAll(theaters);
    notifyDataSetChanged();
  }
}
