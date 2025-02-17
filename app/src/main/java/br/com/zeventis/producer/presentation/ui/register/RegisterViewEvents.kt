package br.com.zeventis.producer.presentation.ui.register

import br.com.zeventis.producer.presentation.model.authentication.User
import br.com.zeventis.producer.presentation.model.register.CompanyRegisterSearchPresentation

open class RegisterViewEvents {

    data class OnRegisterSuccess(val user: User) : RegisterViewEvents()
    data class OnRegisterFailed(val exceptionError: Exception) : RegisterViewEvents()

    class OnGetCompanyNotFound : RegisterViewEvents()
    data class OnGetCompanyFailed(val exceptionError: Exception) : RegisterViewEvents()
    data class OnGetCompanySuccess(val company: List<CompanyRegisterSearchPresentation>) : RegisterViewEvents()
}