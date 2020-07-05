package com.example.bank.bankapp.data.api.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse (
    @SerializedName("message") val message : String,
    @SerializedName("code") val code : Int
)