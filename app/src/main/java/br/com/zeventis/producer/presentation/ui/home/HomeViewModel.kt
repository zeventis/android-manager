package br.com.zeventis.producer.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zeventis.producer.core.plataform.BaseViewModel
import br.com.zeventis.producer.core.plataform.DefaultViewState
import br.com.zeventis.producer.domain.usecase.HomeUseCase
import br.com.zeventis.producer.presentation.mapper.home.HomeEventsMapper
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    private var events = MutableLiveData<HomeViewEvents>()
    val viewEvents: LiveData<HomeViewEvents> = events

    private var states = MutableLiveData<DefaultViewState>()
    val viewState: LiveData<DefaultViewState> = states

    fun getEvents() {
        viewModelScope.launch {
            try {
                states.value = DefaultViewState.ShowLoading()

                val response = homeUseCase.execute()
                events.value = HomeViewEvents.OnGetEventsSuccess(HomeEventsMapper.transformToList(response))

                states.value = DefaultViewState.HideLoading()
            } catch (exception: Exception) {
                events.value = HomeViewEvents.OnGetEventsFailed(exception)
                states.value = DefaultViewState.HideLoading()
            }
        }
    }
}