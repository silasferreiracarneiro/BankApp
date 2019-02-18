package com.example.bank.bankapp.ui.paymentList

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.Toast
import com.example.bank.bankapp.R
import com.example.bank.bankapp.domain.CardList.ListPayment
import com.example.bank.bankapp.domain.CardList.PaymentAdapter
import com.example.bank.bankapp.domain.login.Usuario
import com.example.bank.bankapp.infra.formatutils.DateFormat
import com.example.bank.bankapp.infra.formatutils.MoneyFormat.Companion.currencyFormatMoney
import com.example.bank.bankapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_payment_list.*
import kotlinx.android.synthetic.main.content_payment_list.*
import kotlinx.android.synthetic.main.list_item_payment.*

class PaymentListActivity : AppCompatActivity(), PaymentListContract.View {

    lateinit var paymentListPayment: PaymentListPresenter
    lateinit var paymentAdapter: PaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_list)

        this.paymentListPayment = PaymentListPresenter(this)

        var user = intent.extras.getSerializable("USUARIO") as? Usuario
        setInformationUser(user!!)
    }

    private fun setInformationUser(usuario: Usuario){

        if(usuario != null){
            txt_name_user.text = usuario.name
            txt_conta.text = "${usuario.bankAccount} / ${usuario.agency}"
            txt_saldo.text = "${currencyFormatMoney(usuario.balance)}"
            this.getListPayment(usuario.userId)
        }
    }

    override fun getListPayment(idUser: Int) {
        this.paymentListPayment.getListPayment(idUser)
    }

    override fun setMessageUser(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun loadCardListPayment(list: ArrayList<ListPayment>) {
        this.paymentAdapter = PaymentAdapter(this, list)
        recycler_view.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.adapter = this.paymentAdapter
    }

    fun logout(view: View){
        val i = Intent(this, LoginActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

}
