package com.example.mymovies.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymovies.R;
import com.example.mymovies.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private int mCurrentMenu = R.id.navigation_home;

    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(mMainViewModel);
        binding.recyclerView.setAdapter(new MainListAdapter(this));
        binding.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() != mCurrentMenu) {
                    mCurrentMenu = item.getItemId();
                    mMainViewModel.refreshUIFromNetwork(item.getItemId() == R.id.navigation_bookmarks);
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainViewModel.refreshUIFromNetwork(mCurrentMenu == R.id.navigation_bookmarks);
    }

}