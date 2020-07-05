package com.example.bank.bankapp.ui.paymentList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bank.bankapp.R
import com.example.bank.bankapp.utils.Constants.ID_USER

class PaymentListActivity : AppCompatActivity(), PaymentListContract.View {

    companion object {
        fun newInstance(idUser: Int, context: Context) : Intent {
            val intent = Intent(context, PaymentListActivity::class.java)
            intent.putExtra(ID_USER, idUser)
            return intent
        }
    }

    lateinit var paymentListPayment: PaymentListPresenter
    lateinit var paymentAdapter: PaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_list)
    }
}
