package com.example.bank.bankapp.ui.paymentList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bank.bankapp.R
import com.example.bank.bankapp.model.Payment

class PaymentAdapter (var payments: List<Payment>): RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_payment, parent, false)
        return PaymentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return payments.size
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = payments[position]
        holder.bind(payment)
    }

    class PaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.type_payment)
        private val date: TextView = itemView.findViewById(R.id.date_payment)
        private val descrition: TextView = itemView.findViewById(R.id.what_Payment)
        private val value: TextView = itemView.findViewById(R.id.value_payment)

        fun bind(payments: Payment) {
            this.title.text = payments.title
            this.date.text = payments.date
            this.descrition.text = payments.desc
            this.value.setText(payments.value)
        }
    }
}