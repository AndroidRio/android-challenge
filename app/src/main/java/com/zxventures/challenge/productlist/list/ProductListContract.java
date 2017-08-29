package com.zxventures.challenge.productlist.list;

import android.view.View;

import com.zxventures.challenge.PocCategorySearchQuery;

import java.util.List;

class ProductListContract {

    private ProductListContract() {
    }

    interface ViewContract {

        void showProducts(List<PocCategorySearchQuery.Product> products);

        void showEmptyState();

        void showFailureState();

        void showLoadingState();

        void displayProductDetail(View itemView, int adapterPosition, String categoryId, String pocId);
    }

    interface ModelContract {
        void setPresenter(PresenterModelContract presenter);

        void loadProducts();

        String getPocId();

        String getCategoryId();
    }

    interface PresenterModelContract {

        void onProductsLoaded(List<PocCategorySearchQuery.Product> products);

        void onFailureToLoadProducts();
    }

    interface PresenterViewContract {
        void attachView(ViewContract view);

        void detachView();

        void onViewCreated();

        void onProductClicked(View itemView, int adapterPosition);
    }

    interface ViewAdapter {
        void onProductClicked(View itemView, int adapterPosition);
    }
}