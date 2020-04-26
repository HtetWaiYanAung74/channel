package com.thamardaw.dchannel.app.viewstate.register

sealed class RegisterEvent {
    data class NetworkError(val throwable: Throwable) : RegisterEvent()
}