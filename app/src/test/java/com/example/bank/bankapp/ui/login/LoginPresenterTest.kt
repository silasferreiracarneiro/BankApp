package com.example.bank.bankapp.ui.login

import android.content.Context
import com.example.bank.bankapp.config.Contants
import com.example.bank.bankapp.config.Contants.PASSWORD
import com.example.bank.bankapp.config.Contants.USERNAME
import com.example.bank.bankapp.mock.getResultApiLoginFailCall
import com.example.bank.bankapp.mock.getResultApiLoginSucessCall
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.annotations.Contract
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import kotlin.text.Typography.times

class LoginPresenterTest {

    @MockK
    lateinit var usecase: LoginUsecase

    @Mock
    lateinit var view: LoginContract.View

    @MockK
    lateinit var context: Context

    @Spy
    lateinit var presenter: LoginContract.Presenter

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        MockitoAnnotations.initMocks(this)
        presenter = LoginPresenter(view, context, usecase)
    }

    @Test
    fun `getLastUserLogged - testa retornando um usuario vazio`() {
        coEvery { usecase.getLastUserLogged() } returns mapOf (
            Pair(USERNAME, ""),
            Pair(PASSWORD, "")
        )

        GlobalScope.launch {
            presenter.getLastUserLogged()
            Mockito.verify(view).setLasUserLogged("", "")
        }
    }

    @Test
    fun `getLastUserLogged - testa ultimo usuario que logou no app`() {
        coEvery { usecase.getLastUserLogged() } returns mapOf (
            Pair(USERNAME, USERNAME),
            Pair(PASSWORD, PASSWORD)
        )

        GlobalScope.launch {
            presenter.getLastUserLogged()
            Mockito.verify(view).setLasUserLogged(USERNAME, PASSWORD)
        }
    }

    @Test
    fun `login - tenta efetuar o login mas retorna com o usuario e a sneha invalidos`(){
        coEvery { usecase.validaUsername(USERNAME) } returns true
        coEvery { usecase.validaPassword(USERNAME) } returns true

        GlobalScope.launch {
            presenter.login(USERNAME, PASSWORD)
            Mockito.verify(view).invalidFields()
        }
    }

    @Test
    fun `login - tenta efetuar o login mas retorna com o usuario invalido`(){
        coEvery { usecase.validaUsername(USERNAME) } returns true
        coEvery { usecase.validaPassword(USERNAME) } returns false

        GlobalScope.launch {
            presenter.login(USERNAME, PASSWORD)
            Mockito.verify(view).usernameInvalido()
        }
    }

    @Test
    fun `login - tenta efetuar o login mas retorna com a senha invalida`(){
        coEvery { usecase.validaUsername(USERNAME) } returns false
        coEvery { usecase.validaPassword(USERNAME) } returns true

        GlobalScope.launch {
            presenter.login(USERNAME, PASSWORD)
            Mockito.verify(view).passwordInvalido()
        }
    }

    @Test
    fun `login - efetua o login retornando erro da api`(){
        coEvery { usecase.validaUsername(USERNAME) } returns false
        coEvery { usecase.validaPassword(USERNAME) } returns false
        coEvery { usecase.login(USERNAME, PASSWORD) } returns getResultApiLoginFailCall()

        GlobalScope.launch {
            presenter.login(USERNAME, PASSWORD)
            Mockito.verify(view).errorLogin("Erro ao executar a ação")
        }
    }

    @Test
    fun `login - efetua o login retornando sucesso da api salvando com erro nos preferences`(){
        coEvery { usecase.validaUsername(USERNAME) } returns false
        coEvery { usecase.validaPassword(USERNAME) } returns false
        coEvery { usecase.login(USERNAME, PASSWORD) } returns getResultApiLoginSucessCall()
        coEvery { usecase.saveUserPrefs(USERNAME, PASSWORD) } returns false

        GlobalScope.launch {
            presenter.login(USERNAME, PASSWORD)
            Mockito.verify(view).errorSaveAccountPrefs()
        }
    }

    @Test
    fun `login - efetua o login retornando sucesso da api salvando com sucesso nos preferences`(){
        coEvery { usecase.validaUsername(USERNAME) } returns false
        coEvery { usecase.validaPassword(USERNAME) } returns false
        coEvery { usecase.login(USERNAME, PASSWORD) } returns getResultApiLoginSucessCall()
        coEvery { usecase.saveUserPrefs(USERNAME, PASSWORD) } returns true

        GlobalScope.launch {
            presenter.login(USERNAME, PASSWORD)
            Mockito.verify(view).sucessCallApi(getResultApiLoginSucessCall().value?.parseUserAccountResponseToUserAccount())
        }
    }
}