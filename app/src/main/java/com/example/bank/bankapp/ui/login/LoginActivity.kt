package com.example.bank.bankapp.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.bank.bankapp.R
import com.example.bank.bankapp.ui.paymentList.PaymentListActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var loginPresenter : LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        loginPresenter = LoginPresenter(this)
    }

    fun login(view: View){
        var textPassword = input_password.text?.toString()
        this.validateUSer("Test@1")
    }

    override fun validateUSer(password: String?) {
        loginPresenter.validatePassword(password)
    }

    override fun setMessageUser(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun getActivityPayment() {
        startActivity(Intent(this, PaymentListActivity::class.java))
    }

    override fun getLogin() {
        var username = input_username.text?.toString()
        var textPassword = input_password.text?.toString()
        this.loginPresenter.sendLogin(username!!, textPassword!!)
    }
}
