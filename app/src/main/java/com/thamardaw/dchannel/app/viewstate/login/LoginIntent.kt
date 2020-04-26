package com.thamardaw.dchannel.app.viewstate.login

import com.thamardaw.dchannel.domain.model.Buyer

sealed class LoginIntent {

    data class LoginProcessIntent(val buyer: Buyer) : LoginIntent()


}