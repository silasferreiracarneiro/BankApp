package com.example.bank.bankapp.ui.paymentList

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.bank.bankapp.R
import com.example.bank.bankapp.domain.CardList.ListPayment
import com.example.bank.bankapp.domain.CardList.PaymentAdapter
import kotlinx.android.synthetic.main.content_payment_list.*

class PaymentListActivity : AppCompatActivity(), PaymentListContract.View {

    lateinit var paymentAdapter: PaymentAdapter

    var array = arrayOf(
        ListPayment("1"),
        ListPayment("2"),
        ListPayment("3"),
        ListPayment("4"),
        ListPayment("5")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_list)

        this.paymentAdapter = PaymentAdapter(this, array)
        recycler_view.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.adapter = this.paymentAdapter
    }
}
