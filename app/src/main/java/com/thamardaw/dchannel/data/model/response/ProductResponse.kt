package com.thamardaw.dchannel.data.model.response

data class ProductResponse(
    val productName: String,
    val productDescription: String,
    val unitsInStock: Int,
    val productImg: String,
    val basePrice: Double
)