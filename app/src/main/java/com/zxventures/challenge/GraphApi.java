package com.zxventures.challenge;

import android.content.Context;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.cache.http.DiskLruHttpCacheStore;
import com.apollographql.apollo.cache.http.HttpCacheStore;

import okhttp3.OkHttpClient;

/**
 * Created by Leonardo on 24/08/2017.
 */

public final class GraphApi {

    private static final int CACHE_SIZE = 10 * 1024 * 1024;

    private static GraphApi graphApi;

    private ApolloClient apolloClient;

    public static void init(Context context) {
        graphApi = new GraphApi(context.getApplicationContext());
    }

    public static GraphApi getInstance() {
        if (graphApi == null) {
            throw new IllegalStateException("You must call the init() befre using this method!");
        }

        return graphApi;
    }

    private GraphApi(Context context) {
        HttpCacheStore cacheStore = new DiskLruHttpCacheStore(context.getCacheDir(), CACHE_SIZE);
        apolloClient = ApolloClient.builder()
                .serverUrl(BuildConfig.GRAPHQL_URL)
                .okHttpClient(new OkHttpClient())
                .httpCacheStore(cacheStore)
                .build();
    }
}
