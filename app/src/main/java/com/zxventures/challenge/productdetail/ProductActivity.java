package com.zxventures.challenge.productdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zxventures.challenge.R;

public class ProductActivity extends AppCompatActivity implements ProductContract.ViewContract {

    private static final String ARG_ADAPTER_POSITION = "arg:adapterPosition";
    private static final String ARG_CATEGORY_ID = "arg:categoryId";
    private static final String ARG_POC_ID = "arg:pocId";

    private ProductContract.PresenterViewContract presenter;


    public static Intent newIntent(Context context, int adapterPosition, String categoryId, String pocId) {
        Intent intent = new Intent(context, ProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_ADAPTER_POSITION, adapterPosition);
        bundle.putString(ARG_CATEGORY_ID, categoryId);
        bundle.putString(ARG_POC_ID, pocId);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        if (getIntent().getExtras()!= null) {
            Bundle bundle = getIntent().getExtras();
            int position = bundle.getInt(ARG_ADAPTER_POSITION);
            String categoryId = bundle.getString(ARG_CATEGORY_ID);
            String pocId = bundle.getString(ARG_POC_ID);
            ProductInjector injector = ProductInjector.newBuilder()
                    .withPosition(position)
                    .withCategoryId(categoryId)
                    .withPocId(pocId)
                    .build();
            injector.inject(this);
            presenter = injector.getPresenter();
        } else {
            finish();
        }
    }
}
