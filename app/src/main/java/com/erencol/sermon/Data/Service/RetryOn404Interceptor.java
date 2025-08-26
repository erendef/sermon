package com.erencol.sermon.Data.Service;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RetryOn404Interceptor implements Interceptor {
    private final String fallbackBaseUrl;

    public RetryOn404Interceptor(String fallbackBaseUrl) {
        this.fallbackBaseUrl = fallbackBaseUrl;
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if (response.code() == 404) {
            response.close(); // eski response’u kapatmayı unutma

            // Yeni URL oluştur
            HttpUrl newUrl = request.url().newBuilder()
                    .scheme(Objects.requireNonNull(HttpUrl.parse(fallbackBaseUrl)).scheme())
                    .host(Objects.requireNonNull(HttpUrl.parse(fallbackBaseUrl)).host())
                    .port(Objects.requireNonNull(HttpUrl.parse(fallbackBaseUrl)).port())
                    .build();

            Request newRequest = request.newBuilder()
                    .url(newUrl)
                    .build();

            return chain.proceed(newRequest);
        }

        return response;
    }
}
