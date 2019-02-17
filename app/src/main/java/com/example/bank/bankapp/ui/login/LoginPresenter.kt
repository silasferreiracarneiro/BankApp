package com.example.bank.bankapp.ui.login

import com.example.bank.bankapp.data.RetrofitClient
import com.example.bank.bankapp.data.repository.LoginRepository

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    lateinit var provider: LoginRepository

    init {
        val retrofit = RetrofitClient.instance
        provider = retrofit.create(LoginRepository::class.java)
    }

    override fun validatePassword(password: String) {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        val passwordMatcher = Regex(passwordPattern)
        var validate = passwordMatcher.matches(password) != null

        if(validate)
            view.setMessageUser("Senha inválida")
        else
            view.getActivityPayment()
    }

    override fun sendLogin(password: String, username: String) {

        
    }
}