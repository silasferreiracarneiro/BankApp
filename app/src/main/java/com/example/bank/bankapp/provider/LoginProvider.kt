package com.example.bank.bankapp.provider

import android.content.Context
import com.example.bank.bankapp.ui.login.LoginContract
import com.example.bank.bankapp.ui.login.LoginRepository
import com.example.bank.bankapp.ui.login.LoginUsecase

private fun providerLoginRepository() : LoginRepository {
    return LoginRepository(providerApi())
}

fun providerLoginUsecase(context: Context): LoginContract.Usecase {
    return LoginUsecase(providerLoginRepository(), providerPrefs(context))
}