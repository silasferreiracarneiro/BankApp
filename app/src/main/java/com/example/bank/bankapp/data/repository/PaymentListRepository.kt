package com.example.bank.bankapp.data.repository

import com.example.bank.bankapp.data.dto.PaymenteResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PaymentListRepository {

    @GET("statements/{idUser}")
    fun getPayment(@Path("idUser")idUser : Int) : Call<PaymenteResponseDto>
}