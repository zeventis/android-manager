package br.com.zeventis.managerapp.presentation.ui.addevent

import br.com.zeventis.managerapp.presentation.model.addevent.AddEventResponsePresentation

open class AddEventViewEvents {

    data class OnSaveEventSuccess(val event: AddEventResponsePresentation) :
        AddEventViewEvents()

    data class OnSaveEventFailed(val exceptionError: Exception) : AddEventViewEvents()
}