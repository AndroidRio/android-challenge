package com.zxventures.challenge.productlist.list;

class ProductListModel implements ProductListContract.ModelContract {

    private ProductListContract.PresenterModelContract presenter;
    private String pocId;
    private String categoryId;

    public ProductListModel(String pocId, String categoryId) {
        this.pocId = pocId;
        this.categoryId = categoryId;
    }

    @Override
    public void setPresenter(ProductListContract.PresenterModelContract presenter) {
        this.presenter = presenter;
    }
}