package com.example.bank.bankapp.provider

import com.example.bank.bankapp.data.api.Api
import com.example.bank.bankapp.data.api.config.RetrofitConfig

fun providerApi() : Api {
    return RetrofitConfig().api
}