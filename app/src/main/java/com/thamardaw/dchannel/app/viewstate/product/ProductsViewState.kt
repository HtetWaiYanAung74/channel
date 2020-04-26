package com.thamardaw.dchannel.app.viewstate.product

import com.thamardaw.dchannel.domain.model.ProductsWithCategory

data class ProductsViewState(
    val products: List<ProductsWithCategory> = emptyList(),
    val loadingProducts: Boolean = false,
    val loadProductsError: Throwable? = null
)