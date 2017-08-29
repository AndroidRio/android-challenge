package com.zxventures.challenge.productlist;

import com.zxventures.challenge.AllCategoriesSearchQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by Leonardo on 26/08/2017.
 */
@RunWith(PowerMockRunner.class)
public class ProductListsPresenterTest {

    private ProductListsPresenter subject;

    @Mock
    private ProductListsContract.ModelContract mockModel;
    @Mock
    private ProductListsContract.ViewContract mockView;

    @Before
    public void setup() {
        subject = new ProductListsPresenter(mockModel);
        subject.attachView(mockView);
    }

    @Test
    public void ensurePresenterOrdersViewToShowLoadingAndModelToLoadCategoriesWhenOnCreateIsCalled() {
        subject.onCreate();
        verify(mockView).showLoading();
        verify(mockModel).loadCategories();
    }

    @Test
    public void ensurePresenterOrdersViewToShowCategoriesWhenCategoriesAreLoaded() {
        List<AllCategoriesSearchQuery.AllCategory> fakeCategoryList = new ArrayList<>();
        subject.onCategoriesLoaded(fakeCategoryList);
        verify(mockView).showCategories(eq(fakeCategoryList));
    }
}
