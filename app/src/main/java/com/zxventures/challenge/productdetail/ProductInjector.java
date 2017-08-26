package com.zxventures.challenge.productdetail;

import android.support.annotation.NonNull;

import com.zxventures.challenge.GraphApi;

final class ProductInjector {

    private ProductModel model;
    private ProductPresenter presenter;
    private int position;
    private String categoryId;
    private String pocId;

    private ProductInjector(Builder builder) {
        position = builder.position;
        categoryId = builder.categoryId;
        pocId = builder.pocId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void inject(@NonNull ProductContract.ViewContract view) {
        model = new ProductModel(GraphApi.getInstance(), position, categoryId, pocId);
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

        int position;
        String categoryId;
        String pocId;

        public ProductInjector build() {
            return new ProductInjector(this);
        }

        public Builder withPosition(int position) {
            this.position = position;
            return this;
        }

        public Builder withCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder withPocId(String pocId) {
            this.pocId = pocId;
            return this;
        }
    }
}