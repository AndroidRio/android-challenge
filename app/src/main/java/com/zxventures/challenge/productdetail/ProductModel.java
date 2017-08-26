package com.zxventures.challenge.productdetail;

import com.zxventures.challenge.GraphApi;

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
}