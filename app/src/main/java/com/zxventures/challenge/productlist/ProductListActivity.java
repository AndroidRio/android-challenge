package com.zxventures.challenge.productlist;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ViewSwitcher;

import com.zxventures.challenge.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends AppCompatActivity implements ProductListContract.ViewContract {

    ProductListContract.PresenterViewContract presenter;

    @BindView(R.id.activity_product_list_viewswitcher)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.activity_product_list_viewpager)
    ViewPager categoriesViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        ProductListInjector injector = ProductListInjector.newBuilder()
                .build();
        injector.inject(this);
        presenter = injector.getPresenter();
        presenter.onCreate();
    }

    @Override
    public void showLoading() {
        viewSwitcher.showNext();
    }
}
