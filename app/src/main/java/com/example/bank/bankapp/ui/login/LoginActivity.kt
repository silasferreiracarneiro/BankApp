package com.example.bank.bankapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.bank.bankapp.R
import com.example.bank.bankapp.model.UserAccount

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter : LoginPresenter

    private lateinit var btnLogin: Button
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPresenter()
        bindProperties()
        bindEventProperties()
    }

    private fun initPresenter() {
        presenter = LoginPresenter(this)
    }

    private fun bindProperties() {
        this.btnLogin = findViewById(R.id.btn_login)
        this.edtUsername = findViewById(R.id.input_username)
        this.edtPassword = findViewById(R.id.input_password)
    }

    private fun bindEventProperties() {
        this.btnLogin.setOnClickListener { login() }
    }

    private fun login() {
        val username = edtUsername.text?.toString()
        val password = edtPassword.text?.toString()
        presenter.login(username, password)
    }

    override fun invalidFields() {
        passwordInvalido()
        usernameInvalido()
    }

    override fun passwordInvalido() {
        edtPassword.error = getString(R.string.password_error)
    }

    override fun usernameInvalido() {
        edtUsername.error = getString(R.string.username_error)
    }

    override fun errorLogin(message: String?) {

    }

    override fun sucessCallApi(user: UserAccount?) {

    }
}