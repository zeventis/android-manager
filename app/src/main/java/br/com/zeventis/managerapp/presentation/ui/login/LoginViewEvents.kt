package br.com.zeventis.managerapp.presentation.ui.login

import br.com.zeventis.managerapp.presentation.model.login.User

open class LoginViewEvents {

    data class OnLoginSuccess(val user: User) : LoginViewEvents()

    data class OnLoginFailed(val exceptionError: Exception) : LoginViewEvents()
}