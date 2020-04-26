package com.thamardaw.dchannel.app.feature.product

import androidx.lifecycle.viewModelScope
import com.thamardaw.dchannel.app.base.MviViewModel
import com.thamardaw.dchannel.app.viewstate.product.ProductEvent
import com.thamardaw.dchannel.app.viewstate.product.ProductIntent
import com.thamardaw.dchannel.app.viewstate.product.ProductsPartialState
import com.thamardaw.dchannel.app.viewstate.product.ProductsViewState
import com.thamardaw.dchannel.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getProductsUseCase: GetProductsUseCase = GetProductsUseCase()
) : MviViewModel<ProductsViewState, ProductEvent, ProductIntent>() {


    private var viewState = ProductsViewState()

    override fun sendIntent(intent: ProductIntent) {
        when (intent) {
            is ProductIntent.LoadProductIntent -> loadProduct()
        }
    }

    private fun loadProduct() {
        viewModelScope.launch {
            updateViewState(ProductsPartialState.ProductsLoading)
            getProductsUseCase.invoke().either({
                updateViewState(ProductsPartialState.ProductsError(it))
                emitEvent(ProductEvent.NetworkError(it))
            }, {
                updateViewState(ProductsPartialState.ProductsResult(it))
            })
        }
    }

    private fun updateViewState(state: ProductsPartialState) {
        viewState = state.reduce(viewState)
        viewStateLiveData.postValue(viewState)
    }
}