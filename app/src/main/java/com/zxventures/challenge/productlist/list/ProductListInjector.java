package com.zxventures.challenge.productlist.list;

import android.support.annotation.NonNull;

import com.zxventures.challenge.GraphApi;

final class ProductListInjector {

    private ProductListModel model;
    private ProductListPresenter presenter;
    private String pocId;
    private String categoryId;

    private ProductListInjector(Builder builder) {
        pocId = builder.pocId;
        categoryId = builder.categoryId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void inject(@NonNull ProductListContract.ViewContract view) {
        model = new ProductListModel(pocId, categoryId, GraphApi.getInstance());
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

        private String pocId;
        private String categoryId;

        public ProductListInjector build() {
            return new ProductListInjector(this);
        }

        public Builder withPocId(String pocId) {
            this.pocId = pocId;
            return this;
        }

        public Builder withCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }
    }
}