package br.com.zeventis.managerapp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zeventis.managerapp.core.plataform.BaseViewModel
import br.com.zeventis.managerapp.domain.usecase.HomeUseCase
import br.com.zeventis.managerapp.presentation.mapper.home.HomeEventsMapper
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {

    private var state = MutableLiveData<HomeViewEvents>()
    val viewState: LiveData<HomeViewEvents> = state

    fun getEvents() {
        launch {
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