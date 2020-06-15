package com.erencol.sermon.View.Adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.erencol.sermon.View.Activities.ReadingActivity;
import com.erencol.sermon.R;
import com.bumptech.glide.GenericTransitionOptions;
import com.erencol.sermon.Model.Sermon;
import com.erencol.sermon.View.Activities.MainActivity;
import com.erencol.sermon.View.Adapters.ViewHolders.SermonViewHolder;
import com.erencol.sermon.databinding.SermonCellBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SermonAdapter extends RecyclerView.Adapter<SermonViewHolder> {
    private List<Sermon> sermonList;

    public SermonAdapter() {
        this.sermonList = Collections.emptyList();
    }

    @Override
    public SermonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SermonCellBinding sermonCellBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.sermon_cell, parent, false);
        return new SermonViewHolder(sermonCellBinding);
    }

    @Override
    public void onBindViewHolder(final SermonViewHolder holder, final int position) {
        holder.bindSermon(sermonList.get(position));
    }

    @Override
    public int getItemCount() {
        if(sermonList!=null)
            return sermonList.size();
        else
            return 0;
    }
    public void setSermonList(List<Sermon> sermonList) {
        this.sermonList = sermonList;
        notifyDataSetChanged();
    }




}
