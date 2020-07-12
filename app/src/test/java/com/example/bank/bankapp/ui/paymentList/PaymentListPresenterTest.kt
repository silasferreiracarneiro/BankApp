package com.example.bank.bankapp.ui.paymentList

import com.example.bank.bankapp.mock.getResultApiListPaymentErrorCall
import com.example.bank.bankapp.mock.getResultApiListPaymentSucessCall
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy

@ExperimentalCoroutinesApi
class PaymentListPresenterTest {

    @MockK
    lateinit var usecase: PaymentListUsecase

    @Mock
    lateinit var view: PaymentListContract.View

    @Spy
    lateinit var presenter: PaymentListContract.Presenter

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        MockitoAnnotations.initMocks(this)
        presenter = PaymentListPresenter(view, usecase)
    }

    @Test
    fun `getListPayment - testa quando a api retorna erro`() {
        coEvery { usecase.getListPayment(1) } returns getResultApiListPaymentErrorCall()

        GlobalScope.launch {
            presenter.getListPayment(1)
            Mockito.verify(view).showErrorUser("Erro ao obter a lista")
        }
    }

    @Test
    fun `getListPayment - testa quando a api retorna sucesso`() {
        coEvery { usecase.getListPayment(1) } returns getResultApiListPaymentSucessCall()

        GlobalScope.launch {
            presenter.getListPayment(1)
            Mockito.verify(view).sucessCallApi(getResultApiListPaymentSucessCall().value?.parseStatementResponseToPayment())
        }
    }
}