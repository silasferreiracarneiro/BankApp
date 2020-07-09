package com.example.bank.bankapp.ui.login

import com.example.bank.bankapp.config.Contants
import com.example.bank.bankapp.data.api.Api
import com.example.bank.bankapp.mock.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginRepositoryTest {

    @MockK
    lateinit var api: Api

    private lateinit var repository: LoginRepository

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        this.repository = LoginRepository(api)
    }

    @Test
    fun `login - valida chamada de login quando da sucesso`() {
        coEvery { api.login(getUserRequest()) } returns getResultApiLoginSucess()

        GlobalScope.launch {
            val login = repository.login(Contants.USERNAME, Contants.PASSWORD)
            Assert.assertTrue(
                login.isSucess()
            )
        }
    }

    @Test
    fun `login - valida chamada de login quando da erro na chamada`() {
        coEvery { api.login(getUserRequest()) } returns getResultApiLoginError()

        runBlocking {
            val login = repository.login(Contants.USERNAME, Contants.PASSWORD)
            Assert.assertFalse(
                login.isSucess()
            )
        }
    }
}