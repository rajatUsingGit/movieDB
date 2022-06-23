package com.example.mymovies.data.remote;

import androidx.annotation.NonNull;

import com.example.mymovies.util.Constants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("api_key", Constants.AUTH_KEY)
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }

}
