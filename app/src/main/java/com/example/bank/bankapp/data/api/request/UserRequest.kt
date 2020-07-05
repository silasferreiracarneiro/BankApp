package com.example.bank.bankapp.data.api.request

import com.google.gson.annotations.SerializedName

class UserRequest(
    @SerializedName("user") val user: String,
    @SerializedName("password") val password: String
)