package com.erencol.sermon.View.Activities;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.erencol.sermon.Model.Sermon;
import com.erencol.sermon.R;
import com.erencol.sermon.databinding.ActivityReadingBinding;
import com.erencol.sermon.viewmodelpkg.ReadingViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

public class ReadingActivity extends AppCompatActivity {
    Sermon sermon;
    ActivityReadingBinding activityReadingBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityReadingBinding = DataBindingUtil.setContentView(this, R.layout.activity_reading);
        getExtrasFromIntent();
        setToolbar(sermon.title);
    }


    public void setToolbar(@NonNull String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbar.setTitle(title);
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.white));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
    }



    public void getExtrasFromIntent(){
        sermon = new Sermon();
        sermon = (Sermon) Objects.requireNonNull(getIntent().getExtras()).getSerializable("sermon");
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
