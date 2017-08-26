package com.zxventures.challenge.productlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zxventures.challenge.R;

public class ProductListActivity extends AppCompatActivity implements ProductListContract.ViewContract {

    ProductListContract.PresenterViewContract presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        ProductListInjector injector = ProductListInjector.newBuilder()
                .build();
        injector.inject(this);
        presenter = injector.getPresenter();
        presenter.onCreate();
    }

    @Override
    public void showLoading() {
        //Does nothing.
    }
}
