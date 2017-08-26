package com.zxventures.challenge.productlist;

import android.support.annotation.NonNull;

import com.zxventures.challenge.GraphApi;

final class ProductListsInjector {

    private ProductListsModel model;
    private ProductListsPresenter presenter;

    private ProductListsInjector(Builder builder) {

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void inject(@NonNull ProductListsContract.ViewContract view) {
        model = new ProductListsModel(GraphApi.getInstance());
        presenter = new ProductListsPresenter(model);
        presenter.attachView(view);
        model.setPresenter(presenter);
    }

    public ProductListsPresenter getPresenter() {
        if (presenter == null) {
            throw new IllegalStateException("The method inject must have be called before.");
        }

        return presenter;
    }

    static final class Builder {

        public ProductListsInjector build() {
            return new ProductListsInjector(this);
        }
    }
}