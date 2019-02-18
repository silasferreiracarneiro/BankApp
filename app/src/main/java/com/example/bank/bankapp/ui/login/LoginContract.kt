package com.example.bank.bankapp.ui.login

import android.content.Context
import com.example.bank.bankapp.domain.login.Login
import com.example.bank.bankapp.domain.login.Usuario

interface LoginContract {

    interface Presenter{
        fun validatePassword(password : String?)
        fun sendLogin(password : String, username : String)
        fun inserUser(password : String, username : String)
        fun getUserLocal()
    }

    interface View{
        fun validateUSer(password : String?)
        fun setMessageUser(message: String)
        fun getActivityPayment(usuario: Usuario)
        fun getLogin()
        fun getContext() : Context
        fun getUserLocal()
        fun setUserExiste(login: Login)
    }
}