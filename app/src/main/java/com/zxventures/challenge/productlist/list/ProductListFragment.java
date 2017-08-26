package com.zxventures.challenge.productlist.list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import com.zxventures.challenge.PocCategorySearchQuery;
import com.zxventures.challenge.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductListFragment extends Fragment implements ProductListContract.ViewContract {

    private static final String ARG_CATEGORY_ID = "arg:categoryId";
    private static final String ARG_POC_ID = "arg:pocId";

    private ProductListContract.PresenterViewContract presenter;
    private Unbinder unbinder;

    @BindView(R.id.fragment_product_list_rv)
    RecyclerView productList;
    @BindView(R.id.fragment_product_list_viewswitcher)
    ViewSwitcher viewSwitcher;

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
            ProductListInjector injector = ProductListInjector.newBuilder()
                    .withPocId(pocId)
                    .withCategoryId(categoryId)
                    .build();
            injector.inject(this);
            presenter = injector.getPresenter();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated();
    }

    @Override
    public void showProducts(final List<PocCategorySearchQuery.Product> products) {
        if (getActivity() != null)
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ProductListAdapter adapter = new ProductListAdapter(products);
                    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
                    productList.setLayoutManager(layoutManager);
                    productList.setAdapter(adapter);
                }
            });
    }

    @Override
    public void showEmptyState() {
        if (getActivity() != null)
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    viewSwitcher.showNext();
                }
            });
    }

    @Override
    public void showFailureState() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
