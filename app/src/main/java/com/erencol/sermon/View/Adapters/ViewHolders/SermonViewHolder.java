package com.erencol.sermon.View.Adapters.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erencol.sermon.Model.Sermon;
import com.erencol.sermon.R;
import com.erencol.sermon.databinding.SermonCellBinding;
import com.erencol.sermon.viewmodelpkg.SermonCellViewModel;

public class SermonViewHolder extends RecyclerView.ViewHolder {
    SermonCellBinding sermonCellBinding;

    public TextView sermonTitle,sermonShortText,sermonDate;
    public View view;
    public ImageView sermonImage;

    public SermonViewHolder(SermonCellBinding sermonCellBinding) {
        super(sermonCellBinding.itemSermon);
        this.sermonCellBinding = sermonCellBinding;
    }

    public void bindSermon(Sermon sermon) {
        if (sermonCellBinding.getSermonCellViewModel() == null) {
            sermonCellBinding.setSermonCellViewModel(
                    new SermonCellViewModel(sermon));
        } else {
            sermonCellBinding.getSermonCellViewModel().setSermon(sermon);
        }
    }

}
