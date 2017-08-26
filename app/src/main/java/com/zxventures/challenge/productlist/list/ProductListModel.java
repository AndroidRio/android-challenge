package com.zxventures.challenge.productlist.list;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.zxventures.challenge.GraphApi;
import com.zxventures.challenge.PocCategorySearchQuery;

import javax.annotation.Nonnull;

class ProductListModel implements ProductListContract.ModelContract {

    private ProductListContract.PresenterModelContract presenter;
    private String pocId;
    private String categoryId;
    private GraphApi api;

    public ProductListModel(String pocId, String categoryId, GraphApi instance) {
        this.pocId = pocId;
        this.categoryId = categoryId;
        api = instance;
    }

    @Override
    public void setPresenter(ProductListContract.PresenterModelContract presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadProducts() {
        PocCategorySearchQuery query = PocCategorySearchQuery.builder()
                .id(pocId)
                .search("")
                .categoryId(Integer.valueOf(categoryId))
                .build();
        api.build().query(query).enqueue(new ApolloCall.Callback<PocCategorySearchQuery.Data>() {
            @Override
            public void onResponse(@Nonnull Response<PocCategorySearchQuery.Data> response) {
                if (response.data().poc() != null) {
                    presenter.onProductsLoaded(response.data().poc().products());
                }
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {

            }
        });
    }
}