package com.zxventures.challenge.productdetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zxventures.challenge.R;

public class ProductActivity extends AppCompatActivity implements ProductContract.ViewContract {

    private ProductContract.PresenterViewContract presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ProductInjector injector = ProductInjector.newBuilder()
                .build();
        injector.inject(this);
        presenter = injector.getPresenter();
    }
}
