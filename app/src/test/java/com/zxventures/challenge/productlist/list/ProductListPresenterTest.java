package com.zxventures.challenge.productlist.list;

import android.view.View;

import com.zxventures.challenge.PocCategorySearchQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Leonardo on 26/08/2017.
 */
@RunWith(PowerMockRunner.class)
public class ProductListPresenterTest {

    private ProductListPresenter subject;

    @Mock
    ProductListContract.ViewContract mockView;
    @Mock
    ProductListContract.ModelContract mockModel;

    @Before
    public void setup() {
        subject = new ProductListPresenter(mockModel);
        subject.attachView(mockView);
    }

    @Test
    public void ensurePresenterOrdersViewToShowLoadingStateAndModelToLoadProductsWhenViewIsCreated() {
        subject.onViewCreated();

        verify(mockView).showLoadingState();
        verify(mockModel).loadProducts();
    }

    @Test
    public void ensurePresenterOrdersViewToShowEmptyStateWhenProductListIsEmpty() {
        List<PocCategorySearchQuery.Product> fakeEmptyList = Collections.emptyList();
        subject.onProductsLoaded(fakeEmptyList);

        verify(mockView).showEmptyState();
        verify(mockView, never()).showProducts(eq(fakeEmptyList));
    }

    @Test
    public void ensurePresenterOrdersViewToShowProductsWhenProductListIsNotEmpty() {
        List<PocCategorySearchQuery.Product> fakeList = new ArrayList<>();
        fakeList.add(mock(PocCategorySearchQuery.Product.class));

        subject.onProductsLoaded(fakeList);
        verify(mockView, never()).showEmptyState();
        verify(mockView).showProducts(eq(fakeList));
    }

    @Test
    public void ensurePresenterOrdersViewToShowFailureStateWhenFailedToLoadProducts() {
        subject.onFailureToLoadProducts();

        verify(mockView).showFailureState();
    }

    @Test
    public void ensurePresenterOrdersViewToDisplayProductDetailWhenProductIsClicked() {
        String fakeCategoryId = "fakeCategoryId";
        String fakePocId = "fakePocId";
        int fakePosition = 1;
        when(mockModel.getCategoryId()).thenReturn(fakeCategoryId);
        when(mockModel.getPocId()).thenReturn(fakePocId);

        subject.onProductClicked(mock(View.class), fakePosition);

        verify(mockView).displayProductDetail(any(View.class), anyInt(), eq(fakeCategoryId), eq(fakePocId));
    }
}
