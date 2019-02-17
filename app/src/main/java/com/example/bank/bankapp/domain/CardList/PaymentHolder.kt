package com.example.bank.bankapp.domain.CardList

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.bank.bankapp.R


class PaymentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var typePayment: TextView = this.itemView.findViewById(R.id.typePayment)
    var datePayment: TextView = this.itemView.findViewById(R.id.datePayment)
    var whatPayment: TextView =  this.itemView.findViewById(R.id.whatPayment)
    var valuePayment: TextView = this.itemView.findViewById(R.id.valuePayment)
}