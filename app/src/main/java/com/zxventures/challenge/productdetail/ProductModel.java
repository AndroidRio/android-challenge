package com.zxventures.challenge.productdetail;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.zxventures.challenge.GraphApi;
import com.zxventures.challenge.PocCategorySearchQuery;

import java.util.List;

import javax.annotation.Nonnull;

class ProductModel implements ProductContract.ModelContract {

    private ProductContract.PresenterModelContract presenter;

    private GraphApi api;
    private int position;
    private String categoryId;
    private String pocId;

    public ProductModel(GraphApi instance, int position, String categoryId, String pocId) {
        api = instance;
        this.position = position;
        this.categoryId = categoryId;
        this.pocId = pocId;
    }

    @Override
    public void setPresenter(ProductContract.PresenterModelContract presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadProduct() {
        PocCategorySearchQuery query = PocCategorySearchQuery.builder()
                .id(pocId)
                .search("")
                .categoryId(Integer.valueOf(categoryId))
                .build();
        api.build().query(query).enqueue(new ApolloCall.Callback<PocCategorySearchQuery.Data>() {
            @Override
            public void onResponse(@Nonnull Response<PocCategorySearchQuery.Data> response) {

            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {

            }
        });
    }
}