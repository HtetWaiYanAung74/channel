package com.thamardaw.dchannel.data.datasource.auth.remote

import com.thamardaw.dchannel.data.model.request.BuyerRequest
import com.thamardaw.dchannel.domain.Either
import com.thamardaw.dchannel.domain.model.Buyer



val remoteAuthDataSource by lazy {
    RemoteAuthDataSourceImpl()
}

interface RemoteAuthDataSource {

    suspend fun login(request: BuyerRequest): Either<Throwable, Buyer>

    suspend fun register(request: BuyerRequest): Either<Throwable, Buyer>
}