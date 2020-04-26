package com.thamardaw.dchannel.data.datasource.product.remote

import com.thamardaw.dchannel.domain.Either
import com.thamardaw.dchannel.domain.model.ProductsWithCategory

interface RemoteProductDataSource {
    suspend fun getProductsWithCategory(): Either<Throwable, List<ProductsWithCategory>>

}