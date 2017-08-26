package com.zxventures.challenge.productlist.list;

import android.view.View;

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
        view.showLoadingState();
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

    @Override
    public void onProductClicked(View itemView, int adapterPosition) {
        String categoryId = model.getCategoryId();
        String pocId = model.getPocId();
        view.displayProductDetail(itemView, adapterPosition, categoryId, pocId);
    }
}