package com.zxventures.challenge.productlist;

import com.zxventures.challenge.AllCategoriesSearchQuery;

import java.util.List;

final class ProductListsContract {

    private ProductListsContract() {
    }

    interface ViewContract {

        void showLoading();

        void showCategories(List<AllCategoriesSearchQuery.AllCategory> allCategories);
    }

    interface ModelContract {
        void setPresenter(PresenterModelContract presenter);

        void loadCategories();
    }

    interface PresenterModelContract {

        void onCategoriesLoaded(List<AllCategoriesSearchQuery.AllCategory> allCategories);
    }

    interface PresenterViewContract {
        void attachView(ViewContract view);

        void detachView();

        void onCreate();
    }
}