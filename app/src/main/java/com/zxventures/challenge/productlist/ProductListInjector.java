package com.zxventures.challenge.productlist;

import android.support.annotation.NonNull;

final class ProductListInjector {

    private ProductListModel model;
    private ProductListPresenter presenter;

    private ProductListInjector(Builder builder) {

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void inject(@NonNull ProductListContract.ViewContract view) {
        model = new ProductListModel();
        presenter = new ProductListPresenter(model);
        presenter.attachView(view);
        model.setPresenter(presenter);
    }

    public ProductListPresenter getPresenter() {
        if (presenter == null) {
            throw new IllegalStateException("The method inject must have be called before.");
        }

        return presenter;
    }

    static final class Builder {

        public ProductListInjector build() {
            return new ProductListInjector(this);
        }
    }
}