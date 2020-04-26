package com.thamardaw.dchannel.data.model.request

data class BuyerRequest(
    val phone: String = "",
    val password: String = "",
    val username: String,
    val email: String = "",
    val deliveryAddress: String = "",
    val shopName: String = ""

)