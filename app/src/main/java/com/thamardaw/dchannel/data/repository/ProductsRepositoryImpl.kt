package com.thamardaw.dchannel.data.repository

import com.thamardaw.dchannel.data.datasource.product.remote.RemoteProductDataSource
import com.thamardaw.dchannel.data.datasource.product.remote.remoteProductDataSource
import com.thamardaw.dchannel.domain.Either
import com.thamardaw.dchannel.domain.model.ProductsWithCategory
import com.thamardaw.dchannel.domain.repository.ProductsRepository

val productRepo: ProductsRepository by lazy {
    ProductsRepositoryImpl()
}

class ProductsRepositoryImpl(
    private val productDataSource: RemoteProductDataSource = remoteProductDataSource
) : ProductsRepository {
    override suspend fun getProductsWithCategory(): Either<Throwable, List<ProductsWithCategory>> =
        productDataSource.getProductsWithCategory()

}