package com.example.bank.bankapp.data.api.response

import com.google.gson.annotations.SerializedName

data class StatementListResponse (
	@SerializedName("title") val title : String,
	@SerializedName("desc") val desc : String,
	@SerializedName("date") val date : String,
	@SerializedName("value") val value : Int
)