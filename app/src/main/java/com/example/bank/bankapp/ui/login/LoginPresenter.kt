package com.example.bank.bankapp.ui.login

import com.example.bank.bankapp.data.api.config.ResultApi
import com.example.bank.bankapp.data.api.response.UserAccountResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter(private val view: LoginContract.View, private val usecase: LoginContract.Usecase) : LoginContract.Presenter {

    override fun login(username: String?, password: String?) {
        val usernameResult =  usecase.validaUsername(username)
        val passwordResult = usecase.validaPassword(password)

        afterValidation(
            usernameResult,
            passwordResult,
            username,
            password
        )
    }

    private fun afterValidation(usernameResult: Boolean,
                                passwordResult: Boolean,
                                username: String?,
                                password: String?) {
        if (usernameResult && passwordResult)
            view.invalidFields()
        else if (usernameResult)
            view.usernameInvalido()
        else if (passwordResult)
            view.passwordInvalido()
        else
            effectiveLogin(username?.run { this } ?: "", password?.run { this } ?: "")
    }

    private fun effectiveLogin(username: String, password: String) {
        GlobalScope.launch {
            val login = usecase.login(username, password)
            afterLogin(
                login,
                username,
                password
            )
        }
    }

    private fun afterLogin(
        login: ResultApi<UserAccountResponse>,
        username: String,
        password: String
    ) {
        when (login.isSucess()) {
            true -> salveUserPrefesContinue(login.value, username, password)
            false -> view.errorLogin(login.error?.message)
        }
    }

    private fun salveUserPrefesContinue(
        value: UserAccountResponse?,
        username: String,
        password: String
    ) {
        usecase.saveUserPrefs(username, password)
        view.sucessCallApi(value?.parseUserAccountResponseToUserAccount())
    }
}