package com.thamardaw.dchannel.app.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.thamardaw.dchannel.R

abstract class MviActivity<VM : MviViewModel<VS, E, *>, VS, E> : AppCompatActivity() {


    val offlineAlertDialog by lazy {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_title_offline)
            .setMessage(R.string.alert_msg_offline)
            .setPositiveButton(R.string.alert_dismiss, null)
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpLayout()

        getViewModel().streamViewSates().observe(this) {
            render(it)
        }

        getViewModel().streamEvents().observe(this) {
            renderEvent(it)
        }
    }

    abstract fun setUpLayout()

    abstract fun getViewModel(): VM

    abstract fun render(viewState: VS)

    abstract fun renderEvent(event: E)
}