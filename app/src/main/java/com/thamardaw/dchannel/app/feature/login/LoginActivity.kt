package com.thamardaw.dchannel.app.feature.login

import android.widget.Toast
import androidx.activity.viewModels
import com.thamardaw.dchannel.R
import com.thamardaw.dchannel.app.base.MviActivity
import com.thamardaw.dchannel.app.feature.register.RegisterActivity
import com.thamardaw.dchannel.app.viewstate.login.LoginEvent
import com.thamardaw.dchannel.app.viewstate.login.LoginIntent
import com.thamardaw.dchannel.app.viewstate.login.LoginViewState
import com.thamardaw.dchannel.domain.model.Buyer
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : MviActivity<LoginViewModel, LoginViewState, LoginEvent>() {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun setUpLayout() {
        setContentView(R.layout.activity_login)


        btnLogin.setOnClickListener {

            val name = edtUsername.text.toString()
            val password = edtPassword.text.toString()
            btnLogin.isEnabled = false
            loginIntent(name, password)

        }

        txtRegister.setOnClickListener {
            RegisterActivity.start(this)
        }


    }

    private fun loginIntent(name: String, password: String) {
        loginViewModel.sendIntent(
            LoginIntent.LoginProcessIntent(
                buyer = Buyer(
                    username = name, password = password
                )
            )
        )
    }

    override fun getViewModel(): LoginViewModel {
        return loginViewModel
    }

    override fun render(viewState: LoginViewState) {
        with(viewState) {
            if (loginProcessingState)
                btnLogin.text = getString(R.string.loading)

            if (loginSuccessState) {
                btnLogin.isEnabled = true
                btnLogin.text = getString(R.string.login)
                Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_LONG).show()
            }

            if (loginErrorState != null) {
                btnLogin.isEnabled = true
                Toast.makeText(
                    this@LoginActivity,
                    loginErrorState.localizedMessage?.toString(), Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun renderEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.NetworkError -> {
                if (!offlineAlertDialog.isShowing) {
                    offlineAlertDialog.show()
                }
            }
        }
    }
}