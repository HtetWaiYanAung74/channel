package com.thamardaw.dchannel.data.mapper

import com.thamardaw.dchannel.data.model.request.BuyerRequest
import com.thamardaw.dchannel.data.model.response.ProductListResponse
import com.thamardaw.dchannel.data.model.response.ProductResponse
import com.thamardaw.dchannel.domain.model.Buyer
import com.thamardaw.dchannel.domain.model.Product
import com.thamardaw.dchannel.domain.model.ProductsWithCategory

fun BuyerRequest.toDomain(token: String) =
    Buyer(
        this.phone,
        this.password,
        this.username,
        this.email,
        this.deliveryAddress,
        this.shopName,
        token
    )

fun Buyer.toData() = BuyerRequest(
    this.phone,
    this.password,
    this.username,
    this.email,
    this.deliveryAddress,
    this.shopName
)

fun ProductResponse.toDomain() = Product(
    productName = productName,
    productDescription = productDescription,
    unitsInStock = unitsInStock,
    productImg = productImg,
    basePrice = basePrice
)

fun ProductListResponse.toDomain() = ProductsWithCategory(
    categoryName = categoryName,
    data = data.map {
        it.toDomain()
    }.toMutableList()
)

fun List<ProductListResponse>.toDomain() = map {
    it.toDomain()
}
