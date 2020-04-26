package com.thamardaw.dchannel.data.model.response

data class ProductListResponse(
    val categoryName : String,
    val data : MutableList<ProductResponse>
)