package com.example.bank.bankapp.infra.formatutils

import java.text.SimpleDateFormat
import java.util.*

 class DateFormat {

    companion object {

        fun toDateFormat(date: Date?) = with(date ?: Date()) {
            SimpleDateFormat("dd/MM/yyy").format(this)
        }
    }
}