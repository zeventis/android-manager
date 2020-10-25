package br.com.zeventis.producer.presentation.ui.addevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zeventis.producer.core.plataform.BaseViewModel
import br.com.zeventis.producer.domain.usecase.AddEventUseCase
import br.com.zeventis.producer.presentation.mapper.addevent.AddEventRequestMapper
import br.com.zeventis.producer.presentation.mapper.addevent.AddEventResponseMapper
import br.com.zeventis.producer.presentation.model.addevent.AddEventRequestPresentation
import kotlinx.coroutines.launch

class AddEventViewModel(
    private val addEventUseCase: AddEventUseCase
) : BaseViewModel() {

    private var event = MutableLiveData<AddEventViewEvents>()
    val viewEvent: LiveData<AddEventViewEvents> = event

    private var state = MutableLiveData<AddEventViewState>()
    val viewState: LiveData<AddEventViewState> = state

    fun addEvent(addEventRequestPresentation: AddEventRequestPresentation) {
        viewModelScope.launch {
            try {
                state.value = AddEventViewState.ShowLoading()

                val response = addEventUseCase.execute(AddEventRequestMapper.transformFrom(addEventRequestPresentation))

                event.value = AddEventViewEvents.OnSaveEventSuccess(
                    AddEventResponseMapper.transformTo(response)
                )
                state.value = AddEventViewState.HideLoading(true)
            } catch (exception: Exception) {
                event.value = AddEventViewEvents.OnSaveEventFailed(exception)
                state.value = AddEventViewState.HideLoading(false)
            }
        }
    }
}