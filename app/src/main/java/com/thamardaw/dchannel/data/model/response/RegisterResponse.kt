package com.thamardaw.dchannel.data.model.response

data class RegisterResponse(
    val phone: String,
    val username: String,
    val email: String,
    val deliveryAddress: String,
    val shopName: String
)