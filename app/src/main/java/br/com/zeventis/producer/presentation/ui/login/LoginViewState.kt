package br.com.zeventis.producer.presentation.ui.login

open class LoginViewState {

    class ShowLoading : LoginViewState()
    data class HideLoading(val success: Boolean) : LoginViewState()
}