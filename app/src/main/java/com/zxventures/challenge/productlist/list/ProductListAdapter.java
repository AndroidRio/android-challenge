package com.zxventures.challenge.productlist.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zxventures.challenge.PocCategorySearchQuery;
import com.zxventures.challenge.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Leonardo on 26/08/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private List<PocCategorySearchQuery.Product> productList;

    public ProductListAdapter(List<PocCategorySearchQuery.Product> productList) {
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        PocCategorySearchQuery.Product product = productList.get(position);
        holder.title.setText(product.productVariants().get(0).title());
        holder.price.setText(String.format("R$ %.2f", product.productVariants().get(0).price()));
        Glide.with(holder.itemView)
                .load(product.productVariants().get(0).imageUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_product_image)
        ImageView image;
        @BindView(R.id.item_product_title)
        TextView title;
        @BindView(R.id.item_product_price)
        TextView price;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
