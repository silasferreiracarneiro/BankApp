package com.example.bank.bankapp.ui.login

import android.os.StrictMode
import com.example.bank.bankapp.data.RetrofitClient
import com.example.bank.bankapp.data.dto.LoginDto
import com.example.bank.bankapp.data.repository.LoginRepository
import com.example.bank.bankapp.domain.login.Usuario
import java.util.regex.Pattern

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    var provider: LoginRepository
    var messagePassword: String = "Usuário ou senha inválidos"

    init {
        val retrofit = RetrofitClient.instance
        provider = retrofit.create(LoginRepository::class.java)
    }

    override fun validatePassword(password: String?) {

        if(password.isNullOrEmpty() || password.isNullOrBlank()){
            view.setMessageUser(messagePassword)
            return
        }

        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{4,}\$"

        var pattern = Pattern.compile(passwordPattern)
        var matcher = pattern.matcher(password)
        var result = matcher.matches()

        if(!result){
            view.setMessageUser(messagePassword)
            return
        }
        else
        {
            view.getLogin()
            return
        }
    }

    override fun sendLogin(password: String, username: String) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var user = LoginDto(password, username)

        var call = provider.login(user)
        var response = call.execute()

        if(response.isSuccessful) {
            var body = response.body()

            if(body?.error?.code!! != 0){
                this.view.setMessageUser(body?.error.message)
                return
            }else{
                this.view.getActivityPayment(body?.userAccount?.toDomain()!!)
                return
            }
        }
    }
}