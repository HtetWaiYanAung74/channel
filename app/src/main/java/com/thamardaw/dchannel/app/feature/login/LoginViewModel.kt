package com.thamardaw.dchannel.app.feature.login

import androidx.lifecycle.viewModelScope
import com.thamardaw.dchannel.app.base.MviViewModel
import com.thamardaw.dchannel.app.viewstate.login.LoginEvent
import com.thamardaw.dchannel.app.viewstate.login.LoginIntent
import com.thamardaw.dchannel.app.viewstate.login.LoginPartialState
import com.thamardaw.dchannel.app.viewstate.login.LoginViewState
import com.thamardaw.dchannel.domain.model.Buyer
import com.thamardaw.dchannel.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val authUseCase: AuthUseCase = AuthUseCase()) :
    MviViewModel<LoginViewState, LoginEvent, LoginIntent>() {

    private var viewState: LoginViewState = LoginViewState()

    override fun sendIntent(intent: LoginIntent) {
        when(intent){
            is LoginIntent.LoginProcessIntent -> loginIntent(intent.buyer)
        }

    }

    private fun loginIntent(buyer: Buyer) {
        viewModelScope.launch {
            updateViewState(LoginPartialState.LoginLoading)
            authUseCase.invoke(buyer, "LOGIN").either({
                updateViewState(LoginPartialState.LoginError(it))
                emitEvent(LoginEvent.NetworkError(it))
            }, {
                updateViewState(LoginPartialState.LoginResult(it))
            })
        }
    }

    private fun updateViewState(state: LoginPartialState) {
        viewState = state.reduce(viewState)
        viewStateLiveData.postValue(viewState)
    }
}