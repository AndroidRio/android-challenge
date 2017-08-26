package com.zxventures.challenge.productlist.list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxventures.challenge.R;

public class ProductListFragment extends Fragment {

    public static final String ARG_CATEGORY_ID = "arg:categoryId";
    public static final String ARG_POC_ID = "arg:pocId";

    public ProductListFragment() {
    }

    public static ProductListFragment newInstance(String pocId, String categoryId) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_POC_ID, pocId);
        bundle.putString(ARG_CATEGORY_ID, categoryId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            String pocId = bundle.getString(ARG_POC_ID);
            String categoryId = bundle.getString(ARG_CATEGORY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

}
