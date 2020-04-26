package com.thamardaw.dchannel.domain.usecase

import com.thamardaw.dchannel.data.repository.authRepo
import com.thamardaw.dchannel.domain.model.Buyer
import com.thamardaw.dchannel.domain.repository.AuthRepository

class AuthUseCase
    (
    private val authRepository: AuthRepository = authRepo
) {
    suspend operator fun invoke(request: Buyer, type: String) = when (type) {

        "LOGIN" -> authRepository.login(request)
        "REGISTER" -> authRepository.register(request)
        else -> throw IllegalArgumentException("Unknown type: $type")


    }

}