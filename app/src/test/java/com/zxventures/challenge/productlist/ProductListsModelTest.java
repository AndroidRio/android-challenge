package com.zxventures.challenge.productlist;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.ApolloQueryCall;
import com.apollographql.apollo.api.Response;
import com.zxventures.challenge.AllCategoriesSearchQuery;
import com.zxventures.challenge.GraphApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Leonardo on 26/08/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GraphApi.class, ApolloClient.class, Response.class})
public class ProductListsModelTest {

    private ProductListsModel subject;

    @Mock
    ProductListsContract.PresenterModelContract mockPresenter;
    @Mock
    GraphApi mockApi;
    @Mock
    ApolloClient mockApolloClient;
    @Mock
    ApolloQueryCall mockQueryCall;
    @Mock
    Response<AllCategoriesSearchQuery.Data> mockResponse;
    @Mock
    AllCategoriesSearchQuery.Data mockData;

    @Captor
    private ArgumentCaptor<ApolloCall.Callback<AllCategoriesSearchQuery.Data>> callbackArgumentCaptor;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(GraphApi.class);

        subject = new ProductListsModel(mockApi);
        subject.setPresenter(mockPresenter);
    }

    @Test
    public void ensurePresenterIsCalledWhenCategoriesAreLoadedSuccessfully() {
        when(mockApi.build()).thenReturn(mockApolloClient);
        when(mockApolloClient.query(any(AllCategoriesSearchQuery.class))).thenReturn(mockQueryCall);
        when(mockResponse.data()).thenReturn(mockData);
        when(mockData.allCategory()).thenReturn(new ArrayList<AllCategoriesSearchQuery.AllCategory>());
        subject.loadCategories();

        verify(mockApi).build();
        verify(mockApolloClient).query(any(AllCategoriesSearchQuery.class));
        verify(mockQueryCall).enqueue(callbackArgumentCaptor.capture());

        callbackArgumentCaptor.getValue().onResponse(mockResponse);

        verify(mockPresenter).onCategoriesLoaded(anyListOf(AllCategoriesSearchQuery.AllCategory.class));
    }
}
