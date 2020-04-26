package com.thamardaw.dchannel.domain.model

data class Buyer(
    val phone: String = "",
    val password: String = "",
    val username: String = "",
    val email: String = "",
    val deliveryAddress: String = "",
    val shopName: String = "",
    val token: String = ""
)