package com.thamardaw.dchannel.app.viewstate.register

import com.thamardaw.dchannel.domain.model.Buyer

sealed class RegisterIntent {

    data class RegisterProcessIntent(val buyer: Buyer) : RegisterIntent()


}