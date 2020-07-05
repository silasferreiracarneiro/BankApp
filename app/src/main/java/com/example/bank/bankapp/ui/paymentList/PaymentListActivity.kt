package com.example.bank.bankapp.ui.paymentList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bank.bankapp.R

class PaymentListActivity : AppCompatActivity(), PaymentListContract.View {

    lateinit var paymentListPayment: PaymentListPresenter
    lateinit var paymentAdapter: PaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_list)
    }
}
