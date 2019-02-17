package com.example.bank.bankapp.ui.login

interface LoginContract {

    interface Presenter{
        fun validatePassword(password : String)
        fun sendLogin(password : String, username : String)
    }

    interface View{
        fun validateUSer(password : String)
        fun setMessageUser(message: String)
        fun getActivityPayment()
    }
}