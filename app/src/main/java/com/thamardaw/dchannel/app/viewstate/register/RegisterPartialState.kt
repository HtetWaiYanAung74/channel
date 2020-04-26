package com.thamardaw.dchannel.app.viewstate.register

import com.thamardaw.dchannel.domain.model.Buyer


sealed class RegisterPartialState {
    abstract fun reduce(oldState: RegisterViewState): RegisterViewState


    data class RegisterResult(val buyer: Buyer) : RegisterPartialState() {
        override fun reduce(oldState: RegisterViewState): RegisterViewState =
            oldState.copy(buyer = buyer,registerSuccessState = true,registerProcessingState = false)
    }


    object RegisterLoading : RegisterPartialState() {
        override fun reduce(oldState: RegisterViewState): RegisterViewState =
            oldState.copy(
                registerProcessingState = true,
                registerErrorState = null
            )
    }

    data class RegisterError(val e: Throwable) : RegisterPartialState() {
        override fun reduce(oldState: RegisterViewState): RegisterViewState =
            oldState.copy(
                registerProcessingState = false,
                registerErrorState = e
            )

    }
}