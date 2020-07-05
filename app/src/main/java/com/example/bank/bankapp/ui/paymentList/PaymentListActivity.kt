package com.example.bank.bankapp.ui.paymentList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bank.bankapp.R
import com.example.bank.bankapp.model.Payment
import com.example.bank.bankapp.model.UserAccount
import com.example.bank.bankapp.utils.Constants.USER
import com.example.bank.bankapp.utils.MoneyFormat.Companion.currencyFormatMoney
import com.google.android.material.snackbar.Snackbar

class PaymentListActivity : AppCompatActivity(), PaymentListContract.View {

    companion object {
        fun newInstance(user: UserAccount?, context: Context) : Intent {
            val intent = Intent(context, PaymentListActivity::class.java)
            intent.putExtra(USER, user)
            return intent
        }
    }

    lateinit var presenter: PaymentListContract.Presenter

    private lateinit var coordinatorLayout: ConstraintLayout
    private lateinit var recycler: RecyclerView
    private lateinit var logout: ImageButton
    private lateinit var tvNameUser: TextView
    private lateinit var tvConta: TextView
    private lateinit var tvSaldo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_list)

        initPresenter()
        bindProperties()
        configureEvent()
        getListPayment()
    }

    private fun configureEvent() {
        this.logout.setOnClickListener {
            this.finish()
        }
    }

    private fun initPresenter() {
        presenter = PaymentListPresenter(this)
    }

    private fun bindProperties() {
        this.coordinatorLayout = findViewById(R.id.coordinator_layout)
        this.recycler = findViewById(R.id.recycler_view)
        this.logout = findViewById(R.id.logout)
        this.tvNameUser = findViewById(R.id.txt_name_user)
        this.tvConta = findViewById(R.id.txt_conta)
        this.tvSaldo = findViewById(R.id.txt_saldo)
    }

    private fun getListPayment() {
        val user = intent.extras?.get(USER) as UserAccount
        user.run { presenter.getListPayment(this.userId) }
        configurelayout(user)
    }

    private fun configurelayout(user: UserAccount) {
        this.tvNameUser.text = user.name
        this.tvConta.text = "${user.agency} / ${user.bankAccount}"
        this.tvSaldo.text = currencyFormatMoney(user.balance)
    }

    override fun showErrorUser(message: String?) {
        Snackbar.make(coordinatorLayout, message ?: getString(R.string.error_default), Snackbar.LENGTH_LONG).show()
    }

    override fun sucessCallApi(payments: List<Payment>?) {
        payments?.let {
            configureAdapter(it)
        }
    }

    private fun configureAdapter(list: List<Payment>) {
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.isNestedScrollingEnabled = false
        recycler.adapter = PaymentAdapter(list)
        ((recycler?.adapter as PaymentAdapter).notifyDataSetChanged())
    }
}
