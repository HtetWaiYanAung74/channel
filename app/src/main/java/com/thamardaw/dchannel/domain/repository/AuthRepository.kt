package com.thamardaw.dchannel.domain.repository

import com.thamardaw.dchannel.domain.Either
import com.thamardaw.dchannel.domain.model.Buyer

interface AuthRepository {
    suspend fun login(data: Buyer) : Either<Throwable, Buyer>

    suspend fun register(data: Buyer): Either<Throwable, Buyer>
}