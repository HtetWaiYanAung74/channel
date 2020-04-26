package com.thamardaw.dchannel.data.model.response

import com.thamardaw.dchannel.data.model.request.BuyerRequest

data class LoginResponse(
    val user: BuyerRequest,
    val token: String
)