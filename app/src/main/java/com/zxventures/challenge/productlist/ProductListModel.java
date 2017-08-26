package com.zxventures.challenge.productlist;

class ProductListModel implements ProductListContract.ModelContract {

    ProductListContract.PresenterModelContract presenter;

    @Override
    public void setPresenter(ProductListContract.PresenterModelContract presenter) {
        this.presenter = presenter;
    }
}