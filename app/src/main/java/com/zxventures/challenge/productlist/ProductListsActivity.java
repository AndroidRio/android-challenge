package com.zxventures.challenge.productlist;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ViewSwitcher;

import com.zxventures.challenge.AllCategoriesSearchQuery;
import com.zxventures.challenge.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListsActivity extends AppCompatActivity implements ProductListsContract.ViewContract {

    public static final String POC_ID = "852";

    ProductListsContract.PresenterViewContract presenter;

    @BindView(R.id.activity_product_list_viewswitcher)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.activity_product_list_viewpager)
    ViewPager categoriesViewPager;
    @BindView(R.id.activity_product_list_category_tabs)
    TabLayout categoriesTab;
    @BindView(R.id.activity_product_list_progress)
    ProgressBar progressBar;
    @BindView(R.id.activity_product_list_container_error_state)
    LinearLayout errorStateContainer;

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
        progressBar.setVisibility(View.VISIBLE);
        errorStateContainer.setVisibility(View.GONE);
        viewSwitcher.setDisplayedChild(1);
    }

    @Override
    public void showCategories(final List<AllCategoriesSearchQuery.AllCategory> allCategories) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CategoriesPagerAdapter adapter = new CategoriesPagerAdapter(getSupportFragmentManager(), POC_ID, allCategories);
                categoriesViewPager.setAdapter(adapter);
                categoriesTab.setupWithViewPager(categoriesViewPager);
                viewSwitcher.setDisplayedChild(0);
            }
        });
    }

    @Override
    public void showFailureState() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                errorStateContainer.setVisibility(View.VISIBLE);
                viewSwitcher.setDisplayedChild(1);
            }
        });
    }

    @OnClick(R.id.activity_product_list_retry)
    public void onRetryButtonClicked() {
        presenter.onRetryButtonClicked();
    }
}
