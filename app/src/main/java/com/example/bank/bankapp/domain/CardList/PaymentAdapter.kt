package com.example.bank.bankapp.domain.CardList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bank.bankapp.R
import com.example.bank.bankapp.infra.formatutils.DateFormat.Companion.toDateFormat

class PaymentAdapter(val context: Context, var payments: ArrayList<ListPayment>) : RecyclerView.Adapter<PaymentHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PaymentHolder {
        var view = LayoutInflater.from(this.context).inflate(R.layout.list_item_payment, p0, false)
        return PaymentHolder(view)
    }

    override fun getItemCount(): Int {
        return payments.size
    }

    override fun onBindViewHolder(holder: PaymentHolder, p1: Int) {
        var payment = payments?.get(p1)
        holder.datePayment.text = toDateFormat(payment.date)
        holder.typePayment.text = payment.typePayment
        holder.valuePayment.text = payment.valuePayment.toString()
        holder.whatPayment.text = payment.typeAccount
    }
}