package com.thamardaw.dchannel.app.viewstate.product

import com.thamardaw.dchannel.domain.model.ProductsWithCategory

sealed class ProductsPartialState {
    abstract fun reduce(oldState: ProductsViewState): ProductsViewState

    data class ProductsResult(val products: List<ProductsWithCategory>) : ProductsPartialState() {
        override fun reduce(oldState: ProductsViewState): ProductsViewState =
            oldState.copy(products = products, loadingProducts = false)
    }


    object ProductsLoading : ProductsPartialState() {
        override fun reduce(oldState: ProductsViewState): ProductsViewState =
            oldState.copy(
                loadingProducts = true,
                loadProductsError = null
            )
    }

    data class ProductsError(val e: Throwable) : ProductsPartialState() {
        override fun reduce(oldState: ProductsViewState): ProductsViewState =
            oldState.copy(
                loadingProducts = false,
                loadProductsError = e
            )

    }


}