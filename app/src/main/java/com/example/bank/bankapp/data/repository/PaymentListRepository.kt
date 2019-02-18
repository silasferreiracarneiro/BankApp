package com.example.bank.bankapp.data.repository

import android.os.StrictMode
import com.example.bank.bankapp.data.PaymentListAPI
import com.example.bank.bankapp.infra.RetrofitClient
import com.example.bank.bankapp.data.dto.PaymenteResponseDto
import retrofit2.Response


class PaymentListRepository {

    lateinit var provider: PaymentListAPI

    init {
        val retrofit = RetrofitClient.instance
        provider = retrofit.create(PaymentListAPI::class.java)
    }

    fun getListPayment(idUser: Int): Response<PaymenteResponseDto> {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var call = provider.getPayment(idUser)
        return call.execute()
    }

}