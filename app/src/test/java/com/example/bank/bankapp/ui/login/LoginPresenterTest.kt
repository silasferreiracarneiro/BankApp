package com.example.bank.bankapp.ui.login

import android.content.Context
import com.example.bank.bankapp.config.Contants
import com.example.bank.bankapp.config.Contants.PASSWORD
import com.example.bank.bankapp.config.Contants.USERNAME
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Spy
import kotlin.text.Typography.times

class LoginPresenterTest {

    @MockK
    lateinit var usecase: LoginUsecase

    @MockK
    lateinit var view: LoginContract.View

    @MockK
    lateinit var context: Context

    @Spy
    lateinit var presenter: LoginPresenter

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        presenter = LoginPresenter(view, context, usecase)
    }

    @Test
    fun `getLastUserLogged - testa retornando um usuario null`() {
        coEvery { usecase.getLastUserLogged() } returns mapOf()
        presenter.getLastUserLogged()
        verify {
            view.setLasUserLogged(
                null,
                null
            )
        }
    }

    @Test
    fun `getLastUserLogged - testa ultimo usuario que logou no app`() {
        coEvery { usecase.getLastUserLogged() } returns mapOf( Pair(USERNAME, USERNAME), Pair(PASSWORD, PASSWORD))

        presenter.getLastUserLogged()

        Mockito.verify(view, times(1))
            .setLasUserLogged(USERNAME, PASSWORD)
    }
}