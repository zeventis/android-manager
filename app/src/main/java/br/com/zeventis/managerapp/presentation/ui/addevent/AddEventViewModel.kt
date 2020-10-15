package br.com.zeventis.managerapp.presentation.ui.addevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zeventis.managerapp.core.plataform.BaseViewModel
import br.com.zeventis.managerapp.domain.usecase.AddEventUseCase
import br.com.zeventis.managerapp.presentation.mapper.addevent.AddEventRequestMapper
import br.com.zeventis.managerapp.presentation.mapper.addevent.AddEventResponseMapper
import br.com.zeventis.managerapp.presentation.model.addevent.AddEventRequestPresentation
import kotlinx.coroutines.launch

class AddEventViewModel(
    private val addEventUseCase: AddEventUseCase
) : BaseViewModel() {

    private var state = MutableLiveData<AddEventViewEvents>()
    val viewState: LiveData<AddEventViewEvents> = state

    fun addEvent(addEventRequestPresentation: AddEventRequestPresentation) {
        launch {
            try {
                val response = addEventUseCase.execute(
                    AddEventRequestMapper.transformFrom(addEventRequestPresentation)
                )

                state.value =
                    AddEventViewEvents.OnSaveEventSuccess(
                        AddEventResponseMapper.transformTo(
                            response
                        )
                    )
            } catch (exception: Exception) {
                state.value = AddEventViewEvents.OnSaveEventFailed(exception)
            }
        }
    }
}