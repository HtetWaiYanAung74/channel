package com.thamardaw.dchannel.data.network

import com.thamardaw.dchannel.data.model.request.BuyerRequest
import com.thamardaw.dchannel.data.model.response.LoginResponse
import com.thamardaw.dchannel.data.model.response.ProductListResponse
import com.thamardaw.dchannel.data.model.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST(AUTH_LOGIN)
    suspend fun login(
        @Body data: BuyerRequest
    ): Response<LoginResponse>

    @POST(AUTH_REGISTER)
    suspend fun register(
        @Body data: BuyerRequest
    ): Response<RegisterResponse>

    @GET(PRODUCT_LIST_WITH_CATEGORY)
    suspend fun getProductWithCategory()
            : Response<List<ProductListResponse>>

}