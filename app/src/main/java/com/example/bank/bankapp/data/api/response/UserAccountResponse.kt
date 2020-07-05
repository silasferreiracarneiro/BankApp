package com.example.bank.bankapp.data.api.response

import com.example.bank.bankapp.model.UserAccount
import com.google.gson.annotations.SerializedName

class UserAccountResponse (
    @SerializedName("userAccount") val userAccount : AccountResponse,
    @SerializedName("error") val errorResponse : ErrorResponse
) {

    fun parseUserAccountResponseToUserAccount(): UserAccount {
        return UserAccount(
            userAccount.userId,
            userAccount.name,
            userAccount.bankAccount,
            userAccount.agency,
            userAccount.balance
        )
    }
}