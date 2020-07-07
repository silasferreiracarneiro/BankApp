package com.example.bank.bankapp.utils

import java.text.DecimalFormat

fun Double.currencyFormatMoney(): String {
    val formatter = DecimalFormat("###,###,##0.00")
    return formatter.format(this)
}