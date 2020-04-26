package com.thamardaw.dchannel.app.viewstate.product

sealed class ProductEvent {
    data class NetworkError(val throwable: Throwable) : ProductEvent()
}