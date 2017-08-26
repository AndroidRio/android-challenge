package com.zxventures.challenge.productlist;

class ProductListPresenter implements ProductListContract.PresenterModelContract,
        ProductListContract.PresenterViewContract {

    private ProductListContract.ModelContract model;
    private ProductListContract.ViewContract view;

    ProductListPresenter(ProductListContract.ModelContract model) {
        this.model = model;
    }

    @Override
    public void attachView(ProductListContract.ViewContract view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }

}