package com.example.bank.bankapp.data

import com.example.bank.bankapp.data.dto.LoginDto
import com.example.bank.bankapp.data.dto.UsuarioResponseDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {

    @POST("/api/login")
    fun login(@Body user: LoginDto) : Call<UsuarioResponseDTO>
}