package com.example.bank.bankapp.ui.paymentList

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.bank.bankapp.R
import kotlinx.android.synthetic.main.activity_payment_list.*

class PaymentListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_list)
        setSupportActionBar(toolbar)

    }
}
