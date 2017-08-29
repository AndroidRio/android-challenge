package com.zxventures.challenge.productlist;

import com.zxventures.challenge.AllCategoriesSearchQuery;

import java.util.List;

class ProductListsPresenter implements ProductListsContract.PresenterModelContract,
        ProductListsContract.PresenterViewContract {

    private ProductListsContract.ModelContract model;
    private ProductListsContract.ViewContract view;

    ProductListsPresenter(ProductListsContract.ModelContract model) {
        this.model = model;
    }

    @Override
    public void attachView(ProductListsContract.ViewContract view) {
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

    @Override
    public void onFailureToLoadCategories() {
        view.showFailureState();
    }
}