package br.com.zeventis.producer.presentation.ui.login

import br.com.zeventis.producer.presentation.model.authentication.User

open class LoginViewEvents {

    data class OnLoginSuccess(val user: User) : LoginViewEvents()

    data class OnLoginFailed(val exceptionError: Exception) : LoginViewEvents()
}