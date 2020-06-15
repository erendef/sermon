package com.erencol.sermon.View.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.erencol.sermon.Model.Sermon;
import com.erencol.sermon.R;
import com.erencol.sermon.databinding.ActivityReadingBinding;
import com.erencol.sermon.viewmodelpkg.ReadingViewModel;

public class ReadingActivity extends AppCompatActivity {
    Sermon sermon;
    ActivityReadingBinding activityReadingBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityReadingBinding = DataBindingUtil.setContentView(this, R.layout.activity_reading);
        setToolbar("Dark");
        getExtrasFromIntent();
    }


    public void setToolbar(@NonNull String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbar.setTitle(title);
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.trans));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.trans));
    }

    public void getExtrasFromIntent(){
        sermon = new Sermon();
        sermon = (Sermon) getIntent().getExtras().getSerializable("sermon");
        ReadingViewModel readingViewModel = new ReadingViewModel(sermon);
        activityReadingBinding.setSermonDetailViewModel(readingViewModel);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
