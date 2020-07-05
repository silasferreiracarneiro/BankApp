package com.example.bank.bankapp.provider

import com.example.bank.bankapp.ui.login.LoginContract
import com.example.bank.bankapp.ui.login.LoginRepository
import com.example.bank.bankapp.ui.login.LoginUsecase

private fun providerLoginRepository() : LoginRepository {
    return LoginRepository(providerApi())
}

fun providerLoginUsecase(): LoginContract.Usecase {
    return LoginUsecase(providerLoginRepository(), providerPrefs())
}