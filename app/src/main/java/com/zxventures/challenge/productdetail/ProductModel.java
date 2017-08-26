package com.zxventures.challenge.productdetail;

class ProductModel implements ProductContract.ModelContract {

    private ProductContract.PresenterModelContract presenter;

    @Override
    public void setPresenter(ProductContract.PresenterModelContract presenter) {
        this.presenter = presenter;
    }
}