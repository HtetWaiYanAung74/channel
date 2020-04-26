package com.thamardaw.dchannel.app.viewstate.login

import com.thamardaw.dchannel.domain.model.Buyer

data class LoginViewState(
    val buyer : Buyer = Buyer("","","","","",""),
    val loginProcessingState : Boolean = false,
    val loginErrorState : Throwable?  = null,
    val loginSuccessState : Boolean  = false
)