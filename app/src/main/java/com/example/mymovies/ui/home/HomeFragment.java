package com.example.mymovies.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.adapter.HomeListAdapter;
import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.data.remote.WebApi;
import com.example.mymovies.data.remote.WebResponse;
import com.example.mymovies.databinding.FragmentHomeBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.setLifecycleOwner(this);
        binding.setViewModel(homeViewModel);
        binding.recyclerView.setAdapter(new HomeListAdapter());
        testFetchData(binding.recyclerView);

        return binding.getRoot();
    }

    private void testFetchData(RecyclerView list) {
        Call<WebResponse> responseCall = WebApi.getInstance().getTrendingMovies();
        responseCall.enqueue(new Callback<WebResponse>() {
            @Override
            public void onResponse(Call<WebResponse> call, Response<WebResponse> response) {
                WebResponse webResponse = response.body();
                HomeListAdapter adapter = (HomeListAdapter) list.getAdapter();
                if (webResponse == null || adapter == null) {
                    Log.d("rajatSaysYo", "adapter = " + adapter);
                    return;
                }
                List<MovieItem> movieItems = webResponse.movies;
                adapter.submitList(movieItems);
                Log.d("rajatSaysYo", String.valueOf(movieItems.size()));
            }

            @Override
            public void onFailure(Call<WebResponse> call, Throwable t) {
                Log.e("rajatSaysYo", t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}