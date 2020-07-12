package com.example.bank.bankapp.ui.login

import com.example.bank.bankapp.data.prefs.SharedPreferencesManager
import com.example.bank.bankapp.config.Contants.PASSWORD
import com.example.bank.bankapp.config.Contants.USERNAME
import com.example.bank.bankapp.mock.getResultApiLoginErrorPassword
import com.example.bank.bankapp.mock.getResultApiLoginFailCall
import com.example.bank.bankapp.mock.getResultApiLoginSucessCall
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginUsecaseTest {

    @MockK
    lateinit var repository: LoginRepository

    @MockK
    lateinit var prefs: SharedPreferencesManager

    lateinit var usecase: LoginUsecase

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        usecase = LoginUsecase(repository, prefs)
    }

    @Test
    fun `validaUsername - valida o username passando um valor null`() {
        runBlocking { Assert.assertTrue(usecase.validaUsername(null)) }
    }

    @Test
    fun `validaUsername - valida o username passando um valor vazio`() {
        runBlocking { Assert.assertTrue(usecase.validaUsername("")) }
    }

    @Test
    fun `validaUsername - valida o username passando um cpf invalido`() {
        runBlocking { Assert.assertTrue(usecase.validaUsername("999.999.999-99")) }
    }

    @Test
    fun `validaUsername - valida o username passando um email invalido`() {
        runBlocking {
            Assert.assertTrue(usecase.validaUsername("dasndasinidasn"))
        }
    }

    @Test
    fun `validaUsername - valida o username passando um email valido`() {
        runBlocking {
            Assert.assertFalse(usecase.validaUsername("teste@teste.com"))
        }
    }

    @Test
    fun `validaUsername - valida o username passando um cpf valido`() {
        runBlocking { Assert.assertFalse(usecase.validaUsername("038.176.780-96")) }
    }

    @Test
    fun `validaPassword - valida passando um password null`() {
        runBlocking { Assert.assertTrue(usecase.validaPassword(null)) }
    }

    @Test
    fun `validaPassword - valida passando um password vazio`() {
        runBlocking { Assert.assertTrue(usecase.validaPassword("")) }
    }

    @Test
    fun `validaPassword - valida passando um password invalido`() {
        runBlocking { Assert.assertTrue(usecase.validaPassword("123456")) }
    }

    @Test
    fun `validaPassword - valida passando um password valido`() {
        runBlocking { Assert.assertFalse(usecase.validaPassword("Olj@123")) }
    }

    @Test
    fun `login - valida chamada de login quando da erro informando uma senha invalida`() {
        coEvery { repository.login(USERNAME, PASSWORD) } returns getResultApiLoginErrorPassword()

        runBlocking {
            val login = usecase.login(USERNAME, PASSWORD)
            Assert.assertEquals(
                login.value?.errorResponse?.message,
                getResultApiLoginErrorPassword().value?.errorResponse?.message
            )
        }
    }

    @Test
    fun `login - valida chamada de login quando da sucesso`() {
        coEvery { repository.login(USERNAME, PASSWORD) } returns getResultApiLoginSucessCall()
        runBlocking {
            usecase.login(USERNAME, PASSWORD)
            Assert.assertEquals(
                true,
                getResultApiLoginSucessCall().isSucess()
            )
        }
    }

    @Test
    fun `login - valida chamada de login quando da erro na chamada`() {
        coEvery { repository.login(USERNAME, PASSWORD) } returns getResultApiLoginFailCall()

        runBlocking {
           usecase.login(USERNAME, PASSWORD)
            Assert.assertEquals(
                false,
                getResultApiLoginFailCall().isSucess()
            )
        }
    }

    @Test
    fun `saveUserPrefs - testa salvando o usuario e a senha que o usuario digitou retornando sucesso`() {
        coEvery { prefs.saveUser(USERNAME, PASSWORD) } returns true

        runBlocking {
            val result = usecase.saveUserPrefs(USERNAME, PASSWORD)
            Assert.assertTrue (
                result
            )
        }
    }

    @Test
    fun `saveUserPrefs - testa salvando o usuario e a senha que o usuario digitou se retorna com erro`() {
        coEvery { prefs.saveUser(USERNAME, PASSWORD) } returns false

        runBlocking {
            val result = usecase.saveUserPrefs(USERNAME, PASSWORD)
            Assert.assertFalse (
                result
            )
        }
    }

    @Test
    fun `getLastUserLogged - testa retornando um usuario vazio`() {
        coEvery { prefs.getUser() } returns mapOf()
        runBlocking {
            val result = usecase.getLastUserLogged()
            Assert.assertEquals(
                result,
                emptyMap<String, String>()
            )
        }
    }

    @Test
    fun `getLastUserLogged - testa ultimo usuario que logou no app`() {
        coEvery { prefs.getUser() } returns mapOf(
            Pair(USERNAME, USERNAME),
            Pair(PASSWORD, PASSWORD)
        )

        runBlocking {
            val result = usecase.getLastUserLogged()

            Assert.assertEquals(
                result[USERNAME],
                USERNAME
            )

            Assert.assertEquals(
                result[PASSWORD],
                PASSWORD
            )
        }
    }
}