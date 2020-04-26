package com.thamardaw.dchannel.data.datasource.product.remote

import com.thamardaw.dchannel.data.mapper.toDomain
import com.thamardaw.dchannel.data.network.ApiService
import com.thamardaw.dchannel.data.network.dchannelApis
import com.thamardaw.dchannel.data.network.retrofit
import com.thamardaw.dchannel.data.util.handleCall
import com.thamardaw.dchannel.domain.Either
import com.thamardaw.dchannel.domain.model.ProductsWithCategory
import retrofit2.Retrofit

val remoteProductDataSource by lazy {
    RemoteProductDataSourceImpl()
}

class RemoteProductDataSourceImpl(
    private val client: Retrofit = retrofit,
    private val apiService: ApiService = dchannelApis
) : RemoteProductDataSource {
    override suspend fun getProductsWithCategory(): Either<Throwable, List<ProductsWithCategory>> =
        client.handleCall(
            fallbackErrorMessage = "Failed to load products list",
            apiCall = {
                apiService.getProductWithCategory()

            },
            mapper = {
                it.toDomain()
            }
        )
}