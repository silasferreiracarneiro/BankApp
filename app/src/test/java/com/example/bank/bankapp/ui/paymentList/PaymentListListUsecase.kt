package com.example.bank.bankapp.ui.paymentList

import com.example.bank.bankapp.mock.getResultApiListPaymentEmpty
import com.example.bank.bankapp.mock.getResultApiListPaymentErrorCall
import com.example.bank.bankapp.mock.getResultApiListPaymentSucessCall
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PaymentListListUsecase {

    @MockK
    lateinit var repository: PaymentListRepository

    lateinit var usecase: PaymentListContract.Usecase

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        usecase = PaymentListUsecase(repository)
    }

    @Test
    fun `getListPayment - valida chamada retornando um erro`() {
        coEvery { repository.getListPayment(1) } returns getResultApiListPaymentErrorCall()

        runBlocking {
            val listPayment = usecase.getListPayment(1)
            Assert.assertEquals(
                listPayment.value?.errorResponse?.message,
                getResultApiListPaymentErrorCall().value?.errorResponse?.message
            )
        }
    }

    @Test
    fun `getListPayment - valida chamada retornando body vazio`() {
        coEvery { repository.getListPayment(1) } returns getResultApiListPaymentEmpty()

        runBlocking {
            val listPayment = usecase.getListPayment(1)
            Assert.assertEquals(
                listPayment.value?.statementListResponse?.size,
                getResultApiListPaymentErrorCall().value?.statementListResponse?.size
            )
        }
    }

    @Test
    fun `getListPayment - valida chamada retornando body com sucesso`() {
        coEvery { repository.getListPayment(1) } returns getResultApiListPaymentSucessCall()

        runBlocking {
            val listPayment = usecase.getListPayment(1)
            Assert.assertEquals(
                listPayment.value?.statementListResponse?.size,
                getResultApiListPaymentSucessCall().value?.statementListResponse?.size
            )
        }
    }
}