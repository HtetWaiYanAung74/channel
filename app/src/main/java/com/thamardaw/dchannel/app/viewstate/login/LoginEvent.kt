package com.thamardaw.dchannel.app.viewstate.login

sealed class LoginEvent {
    data class NetworkError(val throwable: Throwable) : LoginEvent()
}