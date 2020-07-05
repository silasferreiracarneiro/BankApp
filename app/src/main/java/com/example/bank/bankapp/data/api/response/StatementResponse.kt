package com.example.bank.bankapp.data.api.response

import com.google.gson.annotations.SerializedName

class StatementResponse (
    @SerializedName("statementList") val statementListResponse : List<StatementListResponse>,
    @SerializedName("error") val errorResponse : ErrorResponse
) { }