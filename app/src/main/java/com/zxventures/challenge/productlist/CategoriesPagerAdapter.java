package com.zxventures.challenge.productlist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zxventures.challenge.AllCategoriesSearchQuery;
import com.zxventures.challenge.productlist.list.ProductListFragment;

import java.util.List;

/**
 * Created by Leonardo on 26/08/2017.
 */

public class CategoriesPagerAdapter extends FragmentStatePagerAdapter {

    private List<AllCategoriesSearchQuery.AllCategory> categoryList;

    public CategoriesPagerAdapter(FragmentManager fm, List<AllCategoriesSearchQuery.AllCategory> categoryList) {
        super(fm);
        this.categoryList = categoryList;
    }

    @Override
    public Fragment getItem(int position) {
        return ProductListFragment.newInstance();
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categoryList.get(position).title();
    }
}
