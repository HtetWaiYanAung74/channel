package com.thamardaw.dchannel.data.repository

import com.thamardaw.dchannel.data.cache.Preferences
import com.thamardaw.dchannel.data.cache.preferencesGateway
import com.thamardaw.dchannel.data.datasource.auth.remote.RemoteAuthDataSource
import com.thamardaw.dchannel.data.datasource.auth.remote.remoteAuthDataSource
import com.thamardaw.dchannel.data.mapper.toData
import com.thamardaw.dchannel.domain.model.Buyer
import com.thamardaw.dchannel.domain.repository.AuthRepository

val authRepo : AuthRepository by lazy {
    AuthRepositoryImpl()
}

class AuthRepositoryImpl(
    private val remoteAuth: RemoteAuthDataSource = remoteAuthDataSource,
    private val cache: Preferences = preferencesGateway
) :
    AuthRepository {

    override suspend fun login(data: Buyer) = remoteAuth.login(data.toData()).also { it ->
        it.suspendEither({}, { cache.save("token", it.token) })
    }


    override suspend fun register(data: Buyer) = remoteAuth.register(data.toData()).also {
        it.suspendEither({}, { })
    }

}