package br.com.zeventis.producer.presentation.ui.addevent

open class AddEventViewState {

    class ShowLoading : AddEventViewState()
    data class HideLoading(val success: Boolean) : AddEventViewState()
}