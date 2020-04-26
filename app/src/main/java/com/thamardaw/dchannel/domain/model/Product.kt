package com.thamardaw.dchannel.domain.model

data class Product(
    val productName: String,
    val productDescription: String,
    val unitsInStock: Int,
    val productImg: String,
    val basePrice: Double
)