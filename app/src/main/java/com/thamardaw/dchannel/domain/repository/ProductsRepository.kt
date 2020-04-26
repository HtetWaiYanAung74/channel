package com.thamardaw.dchannel.domain.repository

import com.thamardaw.dchannel.domain.Either
import com.thamardaw.dchannel.domain.model.ProductsWithCategory

interface ProductsRepository {
    suspend fun getProductsWithCategory(): Either<Throwable, List<ProductsWithCategory>>

}