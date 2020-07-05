package com.example.bank.bankapp.data.api

import com.example.bank.bankapp.data.api.response.UserAccountResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @POST("login")
    fun login(
        @Query("user") user: String,
        @Query("password") password: String
    ): Deferred<UserAccountResponse>

    @GET("statements/{idUser}")
    fun listStatement(
        @Path("idUser")idUser : Int
    )
}