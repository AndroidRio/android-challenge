package com.zxventures.challenge.productdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zxventures.challenge.PocCategorySearchQuery;
import com.zxventures.challenge.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends AppCompatActivity implements ProductContract.ViewContract {

    private static final String ARG_ADAPTER_POSITION = "arg:adapterPosition";
    private static final String ARG_CATEGORY_ID = "arg:categoryId";
    private static final String ARG_POC_ID = "arg:pocId";

    private ProductContract.PresenterViewContract presenter;

    @BindView(R.id.activity_product_image)
    ImageView productImage;
    @BindView(R.id.activity_product_price)
    TextView price;
    @BindView(R.id.activity_product_title)
    TextView title;


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
        ButterKnife.bind(this);
        if (getIntent().getExtras()!= null) {
            supportPostponeEnterTransition();
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
            presenter.onCreate();
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProductDetail(final PocCategorySearchQuery.Product product) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PocCategorySearchQuery.ProductVariant productVariant = product.productVariants().get(0);
                price.setText(String.format("R$ %.2f", productVariant.price()));
                title.setText(productVariant.title());
                Glide.with(ProductActivity.this).load(productVariant.imageUrl()).into(productImage);
                supportStartPostponedEnterTransition();
            }
        });

    }
}
