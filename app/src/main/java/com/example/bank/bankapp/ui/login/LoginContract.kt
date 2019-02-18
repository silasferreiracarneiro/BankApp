package com.example.bank.bankapp.ui.login

import com.example.bank.bankapp.domain.login.Usuario

interface LoginContract {

    interface Presenter{
        fun validatePassword(password : String?)
        fun sendLogin(password : String, username : String)
    }

    interface View{
        fun validateUSer(password : String?)
        fun setMessageUser(message: String)
        fun getActivityPayment(usuario: Usuario)
        fun getLogin()
    }
}