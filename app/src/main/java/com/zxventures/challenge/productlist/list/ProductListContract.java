package com.zxventures.challenge.productlist.list;

import com.zxventures.challenge.PocCategorySearchQuery;

import java.util.List;

class ProductListContract {

    private ProductListContract() {
    }

    interface ViewContract {

        void showProducts(List<PocCategorySearchQuery.Product> products);

        void showEmptyState();

        void showFailureState();
    }

    interface ModelContract {
        void setPresenter(PresenterModelContract presenter);

        void loadProducts();
    }

    interface PresenterModelContract {

        void onProductsLoaded(List<PocCategorySearchQuery.Product> products);

        void onFailureToLoadProducts();
    }

    interface PresenterViewContract {
        void attachView(ViewContract view);

        void detachView();

        void onViewCreated();
    }
}