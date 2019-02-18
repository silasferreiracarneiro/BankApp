package com.example.bank.bankapp.ui.paymentList

import android.os.StrictMode
import com.example.bank.bankapp.data.RetrofitClient
import com.example.bank.bankapp.data.repository.PaymentListRepository
import com.example.bank.bankapp.domain.CardList.ListPayment
import com.example.bank.bankapp.ui.login.LoginContract

class PaymentListPresenter(private val view: PaymentListContract.View): PaymentListContract.Presenter {

    lateinit var provider: PaymentListRepository

    init {
        val retrofit = RetrofitClient.instance
        provider = retrofit.create(PaymentListRepository::class.java)
    }

    override fun getListPayment(idUser: Int) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var call = provider.getPayment(idUser)
        var response = call.execute()

        if(response.isSuccessful) {
            var body = response.body()

            if(body?.error?.code != 0){
                this.view.setMessageUser(body?.error?.message!!)
                return
            }else{
                var list: ArrayList<ListPayment> = arrayListOf()
                body?.statementList?.forEach { list.add(it.toDoamin()) }

                this.view.loadCardListPayment(list)
                return
            }
        }else{
            this.view.setMessageUser(response.message())
            return
        }
    }
}