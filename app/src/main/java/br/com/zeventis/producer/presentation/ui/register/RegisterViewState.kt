package br.com.zeventis.producer.presentation.ui.register

open class RegisterViewState {

    class ShowLoading : RegisterViewState()
    data class HideLoading(val success: Boolean) : RegisterViewState()

    class ShowCompanyLoading : RegisterViewState()
    class HideCompanyLoading : RegisterViewState()
}