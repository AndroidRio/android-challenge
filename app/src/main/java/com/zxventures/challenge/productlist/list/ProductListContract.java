package com.zxventures.challenge.productlist.list;

class ProductListContract {

    private ProductListContract() {
    }

    interface ViewContract {

    }

    interface ModelContract {
        void setPresenter(PresenterModelContract presenter);

        void loadProducts();
    }

    interface PresenterModelContract {

    }

    interface PresenterViewContract {
        void attachView(ViewContract view);

        void detachView();

        void onViewCreated();
    }
}