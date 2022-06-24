package com.example.mymovies.data.remote;

import com.example.mymovies.util.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class WebApi {

    public interface ApiService {
        @GET("movie/popular")
        Call<WebResponse> getTrendingMovies();
    }

    private static volatile ApiService INSTANCE;

    private static final Object sLock = new Object();

    private static final OkHttpClient sClient =  new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor())
            .build();

    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(sClient)
            .build();

    public static ApiService getInstance() {
        if (INSTANCE == null) {
            synchronized (sLock) {
                if (INSTANCE == null) {
                    INSTANCE = sRetrofit.create(ApiService.class);
                }
            }
        }
        return INSTANCE;
    }

}
