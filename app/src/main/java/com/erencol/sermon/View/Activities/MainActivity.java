package com.erencol.sermon.View.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.erencol.sermon.Model.Sermon;
import com.erencol.sermon.R;
import com.erencol.sermon.SermonApplication;
import com.erencol.sermon.View.Adapters.SermonAdapter;
import com.erencol.sermon.databinding.ActivityMainBinding;
import com.erencol.sermon.viewmodelpkg.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class MainActivity extends AppCompatActivity implements Observer {
    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    public void initDataBinding(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainViewModel = new MainViewModel();
        binding.setMainViewModel(mainViewModel);
        binding.setLifecycleOwner(this); // ViewModel'i bağlar.
        setSupportActionBar(binding.toolbar);
        setupListPeopleView(binding.sermonsRecyclerview);
        setupObserver(mainViewModel);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    private void setupListPeopleView(RecyclerView listPeople) {
        SermonAdapter adapter = new SermonAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
        mainViewModel.getSermons();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.inthefutureid:
                showInTheFutureDialog();
                return true;
            case R.id.aboutid:
                showAboutActivity();
                return true;
            case R.id.specialdaysid:
                goToSpecialDaysActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    public void showAboutActivity(){
        Intent i = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(i);
    }

    public void showInTheFutureDialog(){
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.inthefuture))
                .setMessage(getResources().getString(R.string.inthefuture_text))
                .setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info
                )
                .show();
    }

    @Override public void update(Observable observable, Object data) {
        Log.d("update","upadte");
        if (observable instanceof MainViewModel) {
            SermonAdapter sermonAdapter = (SermonAdapter) binding.sermonsRecyclerview.getAdapter();
            MainViewModel mainViewModel = (MainViewModel) observable;
            sermonAdapter.setSermonList(mainViewModel.getSermonList().getValue());
        }
    }

    public void goToSpecialDaysActivity(){
        Intent i = new Intent(MainActivity.this,SpecialDays.class);
        startActivity(i);
    }
}
