package com.zxventures.challenge.productlist;

final class ProductListContract {

    private ProductListContract() {
    }

    interface ViewContract {

        void showLoading();
    }

    interface ModelContract {
        void setPresenter(PresenterModelContract presenter);

        void loadCategories();
    }

    interface PresenterModelContract {

    }

    interface PresenterViewContract {
        void attachView(ViewContract view);

        void detachView();

        void onCreate();
    }
}