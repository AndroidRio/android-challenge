package com.zxventures.challenge.productlist;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.zxventures.challenge.AllCategoriesSearchQuery;
import com.zxventures.challenge.GraphApi;

import javax.annotation.Nonnull;

class ProductListsModel implements ProductListsContract.ModelContract {

    ProductListsContract.PresenterModelContract presenter;
    private GraphApi api;

    public ProductListsModel(GraphApi instance) {
        api = instance;
    }

    @Override
    public void setPresenter(ProductListsContract.PresenterModelContract presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadCategories() {
        AllCategoriesSearchQuery query = AllCategoriesSearchQuery.builder().build();
        api.build().query(query).enqueue(new ApolloCall.Callback<AllCategoriesSearchQuery.Data>() {
            @Override
            public void onResponse(@Nonnull Response<AllCategoriesSearchQuery.Data> response) {
                presenter.onCategoriesLoaded(response.data().allCategory());
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                //Does nothing
            }
        });
    }
}