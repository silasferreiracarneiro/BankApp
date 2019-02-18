package com.example.bank.bankapp.infra.formatutils

import java.text.DecimalFormat

class MoneyFormat {
    companion object {

        fun currencyFormatMoney(amount: Double): String {
            val formatter = DecimalFormat("###,###,##0.00")
            return formatter.format(amount)
        }
    }
}