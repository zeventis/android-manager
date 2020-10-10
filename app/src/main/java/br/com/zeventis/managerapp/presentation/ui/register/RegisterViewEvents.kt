package br.com.zeventis.managerapp.presentation.ui.register

import br.com.zeventis.managerapp.presentation.model.authentication.User

open class RegisterViewEvents {

    data class OnRegisterSuccess(val user: User) : RegisterViewEvents()

    data class OnRegisterFailed(val exceptionError: Exception) : RegisterViewEvents()
}