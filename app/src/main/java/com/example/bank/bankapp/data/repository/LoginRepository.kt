package com.example.bank.bankapp.data.repository

import android.os.StrictMode
import com.example.bank.bankapp.data.LoginAPI
import com.example.bank.bankapp.infra.RetrofitClient
import com.example.bank.bankapp.data.dto.UsuarioResponseDTO
import com.example.bank.bankapp.data.dto.LoginDto
import retrofit2.Response

class LoginRepository {

    var provider: LoginAPI

    init {
        val retrofit = RetrofitClient.instance
        provider = retrofit.create(LoginAPI::class.java)
    }

    fun sendLogin(usuario: LoginDto) : Response<UsuarioResponseDTO> {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var call = provider.login(usuario)
        var response = call.execute()
        return response
    }
}