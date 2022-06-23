package com.example.mymovies.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymovies.R;
import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(mainViewModel);
        binding.recyclerView.setAdapter(new MainListAdapter(this));
        binding.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mainViewModel.processBottomNav(item.getItemId() != R.id.navigation_home);
                return true;
            }
        });
    }

}