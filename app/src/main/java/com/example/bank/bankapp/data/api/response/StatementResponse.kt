package com.example.bank.bankapp.data.api.response

import com.example.bank.bankapp.model.Payment
import com.google.gson.annotations.SerializedName

class StatementResponse (
    @SerializedName("statementList") val statementListResponse : List<StatementListResponse>,
    @SerializedName("error") val errorResponse : ErrorResponse
) {

    fun parseStatementResponseToPayment(): List<Payment> {
        return this.statementListResponse.map {
            Payment(
                it.title,
                it.desc,
                it.date,
                it.value
            )
        }
    }
}