package com.zxventures.challenge.productlist;

import com.zxventures.challenge.AllCategoriesSearchQuery;

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
    public void onCreate() {
        view.showLoading();
        model.loadCategories();
    }

    @Override
    public void onCategoriesLoaded(List<AllCategoriesSearchQuery.AllCategory> allCategories) {
        view.showCategories(allCategories);
    }
}