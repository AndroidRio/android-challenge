package com.zxventures.challenge.productdetail;

import com.zxventures.challenge.PocCategorySearchQuery;

class ProductContract {

    private ProductContract() {
    }

    interface ViewContract {

        void showProductDetail(PocCategorySearchQuery.Product product);
    }

    interface ModelContract {
        void setPresenter(PresenterModelContract presenter);

        void loadProduct();
    }

    interface PresenterModelContract {

        void onProductLoaded(PocCategorySearchQuery.Product product);
    }

    interface PresenterViewContract {
        void attachView(ViewContract view);

        void detachView();

        void onCreate();
    }
}