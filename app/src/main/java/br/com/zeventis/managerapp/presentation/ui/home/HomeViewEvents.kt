package br.com.zeventis.managerapp.presentation.ui.home

import br.com.zeventis.managerapp.presentation.model.home.HomeEvents

open class HomeViewEvents {

    data class OnGetEventsSuccess(val eventsList: List<HomeEvents>) : HomeViewEvents()

    data class OnGetEventsFailed(val exceptionError: Exception) : HomeViewEvents()
}