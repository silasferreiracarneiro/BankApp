package com.example.bank.bankapp.ui.login

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.example.bank.bankapp.R
import com.example.bank.bankapp.model.UserAccount
import com.example.bank.bankapp.ui.paymentList.PaymentListActivity.Companion.newInstance
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter : LoginPresenter

    private lateinit var coordinatorLayout: ConstraintLayout
    private lateinit var btnLogin: CircularProgressButton
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initPresenter()
        bindProperties()
        bindEventProperties()
        presenter.getLastUserLogged()
    }

    private fun initPresenter() {
        presenter = LoginPresenter( this, this)
    }

    private fun bindProperties() {
        this.coordinatorLayout = findViewById(R.id.coordinator_layout)
        this.btnLogin = findViewById(R.id.btn_login)
        this.edtUsername = findViewById(R.id.input_username)
        this.edtPassword = findViewById(R.id.input_password)
    }

    private fun bindEventProperties() {
        this.btnLogin.setOnClickListener { login() }
    }

    private fun login() {
        btnLogin.startAnimation()
        val username = edtUsername.text?.toString()
        val password = edtPassword.text?.toString()
        presenter.login(username, password)
    }

    override fun invalidFields() {
        passwordInvalido()
        usernameInvalido()
    }

    override fun passwordInvalido() {
        this.btnLogin.revertAnimation()
        edtPassword.error = getString(R.string.password_error)
    }

    override fun usernameInvalido() {
        this.btnLogin.revertAnimation()
        edtUsername.error = getString(R.string.username_error)
    }

    override fun errorLogin(message: String?) {
        this.btnLogin.revertAnimation()
        Snackbar.make(coordinatorLayout, message ?: getString(R.string.error_default), Snackbar.LENGTH_LONG).show()
    }

    override fun sucessCallApi(user: UserAccount?) {
        startActivity(user?.let { newInstance(it, this) })
    }

    override fun setLasUserLogged(username: String?, password: String?) {
        edtUsername.setText(username)
        edtPassword.setText(password)
    }

    override fun errorSaveAccountPrefs() {
        this.btnLogin.revertAnimation()
        Snackbar.make(coordinatorLayout, getString(R.string.error_salve_user_prefs), Snackbar.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        this.btnLogin.revertAnimation()
    }
}