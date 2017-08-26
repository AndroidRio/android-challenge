package com.zxventures.challenge.productlist.list;

import com.zxventures.challenge.PocCategorySearchQuery;

import java.util.List;

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

    @Override
    public void onViewCreated() {
        model.loadProducts();
    }

    @Override
    public void onProductsLoaded(List<PocCategorySearchQuery.Product> products) {
        if (products.isEmpty()) {
            view.showEmptyState();
        } else {
            view.showProducts(products);
        }
    }

    @Override
    public void onFailureToLoadProducts() {
        view.showFailureState();
    }
}