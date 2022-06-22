package com.example.mymovies.data.remote;

import androidx.annotation.NonNull;

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
                .addQueryParameter("api_key",
                        "3f811bd59eeb7d8584bc7451725265b4")
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
