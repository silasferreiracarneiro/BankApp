package com.example.bank.bankapp.ui.paymentList

import com.example.bank.bankapp.data.api.Api
import com.example.bank.bankapp.mock.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PaymentListRepositoryTest {

    @MockK
    lateinit var api: Api

    lateinit var repository: PaymentListRepository

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        this.repository = PaymentListRepository(api)
    }

    @Test
    fun `getListPayment - valida chamada retornando um erro`() {
        coEvery { api.listStatement(1) } returns getResultApiPaymentError()

        runBlocking {
            val listPayment = repository.getListPayment(1)
            Assert.assertEquals(
                "Erro ao obter a lista",
                listPayment.value?.errorResponse?.message
            )
        }
    }

    @Test
    fun `getListPayment - valida chamada retornando body vazio`() {
        coEvery { api.listStatement(1) } returns getResultApiPaymentEmpty()

        runBlocking {
            val listPayment = repository.getListPayment(1)
            Assert.assertEquals(
                0,
                listPayment.value?.statementListResponse?.size
            )
        }
    }

    @Test
    fun `getListPayment - valida chamada retornando body com sucesso`() {
        coEvery { api.listStatement(1) } returns getResultApiPaymentSucess()

        runBlocking {
            val listPayment = repository.getListPayment(1)
            Assert.assertEquals(
                9,
                listPayment.value?.statementListResponse?.size
            )
        }
    }
}