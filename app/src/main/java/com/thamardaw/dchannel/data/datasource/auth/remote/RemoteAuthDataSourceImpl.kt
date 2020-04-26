package com.thamardaw.dchannel.data.datasource.auth.remote

import com.thamardaw.dchannel.data.mapper.toDomain
import com.thamardaw.dchannel.data.model.request.BuyerRequest
import com.thamardaw.dchannel.data.network.ApiService
import com.thamardaw.dchannel.data.network.dchannelApis
import com.thamardaw.dchannel.data.network.retrofit
import com.thamardaw.dchannel.data.util.handleCall
import com.thamardaw.dchannel.domain.model.Buyer
import retrofit2.Retrofit

class RemoteAuthDataSourceImpl(
    private val client: Retrofit = retrofit,
    private val apiService: ApiService = dchannelApis
) : RemoteAuthDataSource {

    override suspend fun login(request: BuyerRequest) = client.handleCall(
        fallbackErrorMessage = "Failed to login",
        apiCall = {
            apiService.login(
                request
            )
        },
        mapper = {
            it.user.toDomain(it.token)
        }
    )

    override suspend fun register(request: BuyerRequest) = client.handleCall(
        fallbackErrorMessage = "Failed to register",
        apiCall = {
            apiService.register(
                request
            )
        },
        mapper = {
            Buyer(it.phone, "", it.username, it.email, it.deliveryAddress, it.shopName)
        }
    )

}