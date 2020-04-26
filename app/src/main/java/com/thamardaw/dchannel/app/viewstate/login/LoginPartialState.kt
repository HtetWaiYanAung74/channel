package com.thamardaw.dchannel.app.viewstate.login

import com.thamardaw.dchannel.domain.model.Buyer


sealed class LoginPartialState {
    abstract fun reduce(oldState: LoginViewState): LoginViewState


    data class LoginResult(val buyer: Buyer) : LoginPartialState() {
        override fun reduce(oldState: LoginViewState): LoginViewState =
            oldState.copy(buyer = buyer, loginSuccessState = true, loginProcessingState = false)
    }


    object LoginLoading : LoginPartialState() {
        override fun reduce(oldState: LoginViewState): LoginViewState =
            oldState.copy(
                loginProcessingState = true,
                loginErrorState = null
            )
    }

    data class LoginError(val e: Throwable) : LoginPartialState() {
        override fun reduce(oldState: LoginViewState): LoginViewState =
            oldState.copy(
                loginProcessingState = false,
                loginErrorState = e
            )

    }
}