package br.com.zeventis.producer.presentation.ui.home

import br.com.zeventis.producer.presentation.model.home.HomeEvents

open class HomeViewEvents {

    data class OnGetEventsSuccess(val eventsList: List<HomeEvents>) : HomeViewEvents()

    data class OnGetEventsFailed(val exceptionError: Exception) : HomeViewEvents()

    class OnGetEventsEmpty : HomeViewEvents()
}