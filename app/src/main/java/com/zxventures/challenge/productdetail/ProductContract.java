package com.zxventures.challenge.productdetail;

class ProductContract {

    private ProductContract() {
    }

    interface ViewContract {

    }

    interface ModelContract {
        void setPresenter(PresenterModelContract presenter);
    }

    interface PresenterModelContract {

    }

    interface PresenterViewContract {
        void attachView(ViewContract view);

        void detachView();
    }
}