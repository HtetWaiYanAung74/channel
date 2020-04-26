package com.thamardaw.dchannel.domain.model

data class ProductsWithCategory(
    val categoryName: String,
    val data: MutableList<Product>
)