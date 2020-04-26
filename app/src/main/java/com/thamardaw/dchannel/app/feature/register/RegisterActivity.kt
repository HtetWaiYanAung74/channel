package com.thamardaw.dchannel.app.feature.register

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.thamardaw.dchannel.R
import com.thamardaw.dchannel.app.base.MviActivity
import com.thamardaw.dchannel.app.viewstate.register.RegisterEvent
import com.thamardaw.dchannel.app.viewstate.register.RegisterIntent
import com.thamardaw.dchannel.app.viewstate.register.RegisterViewState
import com.thamardaw.dchannel.domain.model.Buyer
import kotlinx.android.synthetic.main.activity_register.*
import kotlin.concurrent.thread

class RegisterActivity : MviActivity<RegisterViewModel, RegisterViewState, RegisterEvent>() {

    private val registerViewModel by viewModels<RegisterViewModel>()


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }

    override fun setUpLayout() {
        this.setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {

            val name = edtName.text.toString()
            val phone = edtPhoneNumber.text.toString()
            val email = edtEmail.text.toString()
            val address =
                edtDeliverAddress.text.toString() + "," + edtTownship.text.toString() + "," + edtCity.text.toString()
            val shop = edtShopName.text.toString()
            val password = edtPassword.text.toString()

            val buyer = Buyer(
                phone = phone,
                username = name,
                password = password,
                email = email,
                deliveryAddress = address,
                shopName = shop
            )


            registerViewModel.sendIntent(RegisterIntent.RegisterProcessIntent(buyer))


        }

        imgBack.setOnClickListener {
            onBackPressed()
        }


    }


    override fun getViewModel(): RegisterViewModel = registerViewModel

    override fun render(viewState: RegisterViewState) {

        with(viewState) {
            progressBar.visibility = if (registerProcessingState) View.VISIBLE else View.GONE
            if (registerErrorState != null) {
                Toast.makeText(
                    applicationContext,
                    registerErrorState?.localizedMessage.toString(),
                    Toast.LENGTH_LONG
                )
                    .show()
            }

            if (registerSuccessState) {
                Toast.makeText(applicationContext, "Success Register", Toast.LENGTH_LONG).show()

            }
        }


    }

    override fun renderEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.NetworkError -> {
                if (!offlineAlertDialog.isShowing) {
                    offlineAlertDialog.show()
                }
            }
        }
    }
}