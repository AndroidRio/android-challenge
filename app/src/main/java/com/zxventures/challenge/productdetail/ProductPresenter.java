package com.zxventures.challenge.productdetail;

import com.zxventures.challenge.PocCategorySearchQuery;

class ProductPresenter implements ProductContract.PresenterModelContract,
        ProductContract.PresenterViewContract {

    private ProductContract.ModelContract model;
    private ProductContract.ViewContract view;

    ProductPresenter(ProductContract.ModelContract model) {
        this.model = model;
    }

    @Override
    public void attachView(ProductContract.ViewContract view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void onCreate() {
        model.loadProduct();
    }

    @Override
    public void onProductLoaded(PocCategorySearchQuery.Product product) {
        view.showProductDetail(product);
    }
}