package com.thamardaw.dchannel

import android.app.Application
import com.thamardaw.dchannel.domain.Domain

class DChannelApp  : Application(){

    override fun onCreate() {
        super.onCreate()
        Domain.integrateWith(this)
    }
}