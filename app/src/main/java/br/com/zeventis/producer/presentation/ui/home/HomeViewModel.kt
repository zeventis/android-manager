package br.com.zeventis.producer.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zeventis.producer.core.plataform.BaseViewModel
import br.com.zeventis.producer.domain.usecase.HomeUseCase
import br.com.zeventis.producer.presentation.mapper.home.HomeEventsMapper
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    private var state = MutableLiveData<HomeViewEvents>()
    val viewState: LiveData<HomeViewEvents> = state

    fun getEvents() {
        viewModelScope.launch {
            try {
                val response = homeUseCase.execute()
                state.value =
                    HomeViewEvents.OnGetEventsSuccess(HomeEventsMapper.transformToList(response))
            } catch (exception: Exception) {
                state.value = HomeViewEvents.OnGetEventsFailed(exception)
            }
        }
    }
}