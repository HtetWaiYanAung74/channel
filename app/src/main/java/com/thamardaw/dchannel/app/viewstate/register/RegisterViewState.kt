package com.thamardaw.dchannel.app.viewstate.register

import com.thamardaw.dchannel.domain.model.Buyer

data class RegisterViewState(
    val buyer : Buyer = Buyer("","","","","",""),
    val registerProcessingState : Boolean = false,
    val registerErrorState : Throwable?  = null,
    val registerSuccessState : Boolean = false
)