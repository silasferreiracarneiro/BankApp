package com.example.bank.bankapp.ui.login

import com.example.bank.bankapp.data.prefs.SharedPreferencesManager
import com.example.bank.bankapp.utils.isValidEmail
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginUsecaseTest {

    @Mock
    lateinit var repository: LoginRepository

    @Mock
    lateinit var prefs: SharedPreferencesManager

    lateinit var usecase: LoginUsecase

    @Before
    fun init() {
        usecase = LoginUsecase(repository, prefs)
    }

    @Test
    fun `validaUsername - valida o username passando um valor null`() {
        Assert.assertTrue(usecase.validaUsername(null))
    }

    @Test
    fun `validaUsername - valida o username passando um valor vazio`() {
        Assert.assertTrue(usecase.validaUsername(""))
    }

    @Test
    fun `validaUsername - valida o username passando um cpf invalido`() {
        Assert.assertTrue(usecase.validaUsername("999.999.999-99"))
    }

    @Test
    fun `validaUsername - valida o username passando um email invalido`() {
        Assert.assertTrue(usecase.validaUsername("dasndasinidasn"))
    }

    @Test
    fun `validaUsername - valida o username passando um email valido`() {
        Assert.assertFalse(usecase.validaUsername("teste@teste.com"))
    }

    @Test
    fun `validaUsername - valida o username passando um cpf valido`() {
        Assert.assertFalse(usecase.validaUsername("038.176.780-96"))
    }

    @Test
    fun `validaPassword - valida passando um password null`() {
        Assert.assertTrue(usecase.validaPassword(null))
    }

    @Test
    fun `validaPassword - valida passando um password vazio`() {
        Assert.assertTrue(usecase.validaPassword(""))
    }

    @Test
    fun `validaPassword - valida passando um password invalido`() {
        Assert.assertTrue(usecase.validaPassword("123456"))
    }

    @Test
    fun `validaPassword - valida passando um password valido`() {
        Assert.assertFalse(usecase.validaPassword("Olj@123"))
    }
}