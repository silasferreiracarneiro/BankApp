package com.example.bank.bankapp.data.api.response

import com.google.gson.annotations.SerializedName

data class AccountResponse (

	@SerializedName("userId") val userId : Int,
	@SerializedName("name") val name : String,
	@SerializedName("bankAccount") val bankAccount : Int,
	@SerializedName("agency") val agency : Int,
	@SerializedName("balance") val balance : Double
)