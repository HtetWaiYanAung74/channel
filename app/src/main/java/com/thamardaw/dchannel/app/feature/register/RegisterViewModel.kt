package com.thamardaw.dchannel.app.feature.register

import androidx.lifecycle.viewModelScope
import com.thamardaw.dchannel.app.base.MviViewModel
import com.thamardaw.dchannel.app.viewstate.register.RegisterEvent
import com.thamardaw.dchannel.app.viewstate.register.RegisterIntent
import com.thamardaw.dchannel.app.viewstate.register.RegisterPartialState
import com.thamardaw.dchannel.app.viewstate.register.RegisterViewState
import com.thamardaw.dchannel.domain.model.Buyer
import com.thamardaw.dchannel.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(private val authUseCase: AuthUseCase = AuthUseCase()) :
    MviViewModel<RegisterViewState, RegisterEvent, RegisterIntent>() {

    private var viewState: RegisterViewState = RegisterViewState()

    override fun sendIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.RegisterProcessIntent -> registerIntent(intent.buyer)
        }

    }

    private fun registerIntent(buyer: Buyer) {
        viewModelScope.launch {
            updateViewState(RegisterPartialState.RegisterLoading)
            authUseCase.invoke(buyer, "REGISTER").either({
                updateViewState(RegisterPartialState.RegisterError(it))
                emitEvent(RegisterEvent.NetworkError(it))
            }, {
                updateViewState(RegisterPartialState.RegisterResult(it))
            })
        }
    }

    private fun updateViewState(state: RegisterPartialState) {
        viewState = state.reduce(viewState)
        viewStateLiveData.postValue(viewState)
    }
}