package com.zxventures.challenge.productlist;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ViewSwitcher;

import com.zxventures.challenge.AllCategoriesSearchQuery;
import com.zxventures.challenge.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListsActivity extends AppCompatActivity implements ProductListsContract.ViewContract {

    ProductListsContract.PresenterViewContract presenter;

    @BindView(R.id.activity_product_list_viewswitcher)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.activity_product_list_viewpager)
    ViewPager categoriesViewPager;
    @BindView(R.id.activity_product_list_category_tabs)
    TabLayout categoriesTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        ProductListsInjector injector = ProductListsInjector.newBuilder()
                .build();
        injector.inject(this);
        presenter = injector.getPresenter();
        presenter.onCreate();
    }

    @Override
    public void showLoading() {
        viewSwitcher.showNext();
    }

    @Override
    public void showCategories(final List<AllCategoriesSearchQuery.AllCategory> allCategories) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CategoriesPagerAdapter adapter = new CategoriesPagerAdapter(getSupportFragmentManager(), "848", allCategories);
                categoriesViewPager.setAdapter(adapter);
                categoriesTab.setupWithViewPager(categoriesViewPager);
                viewSwitcher.showPrevious();
            }
        });
    }
}
