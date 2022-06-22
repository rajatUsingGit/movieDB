package com.example.mymovies.data.remote;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class WebApi {

    public interface ApiService {
        @GET("movie/popular")
        Call<WebResponse> getTrendingMovies();

        @GET("movie/now_playing")
        Call<WebResponse> getNowPlayingMovies();
    }

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static ApiService sInstance;

    private static final Object sLock = new Object();

    private static final OkHttpClient client =  new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor())
            .build();

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    public static ApiService getInstance() {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = retrofit.create(ApiService.class);
            }
            return sInstance;
        }
    }

}
