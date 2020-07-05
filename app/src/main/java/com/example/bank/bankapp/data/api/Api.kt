package com.example.bank.bankapp.data.api

import com.example.bank.bankapp.data.api.request.UserRequest
import com.example.bank.bankapp.data.api.response.UserAccountResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface Api {

    @POST("login")
    fun login(
        @Body user: UserRequest
    ): Deferred<UserAccountResponse>

    @GET("statements/{idUser}")
    fun listStatement(
        @Path("idUser")idUser : Int
    )
}