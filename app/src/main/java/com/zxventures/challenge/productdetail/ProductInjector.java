package com.zxventures.challenge.productdetail;

import android.support.annotation.NonNull;

final class ProductInjector {

    private ProductModel model;
    private ProductPresenter presenter;

    private ProductInjector(Builder builder) {

    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void inject(@NonNull ProductContract.ViewContract view) {
        model = new ProductModel();
        presenter = new ProductPresenter(model);
        presenter.attachView(view);
        model.setPresenter(presenter);
    }

    public ProductPresenter getPresenter() {
        if (presenter == null) {
            throw new IllegalStateException("The method inject must have be called before.");
        }

        return presenter;
    }

    static final class Builder {

        public ProductInjector build() {
            return new ProductInjector(this);
        }
    }
}