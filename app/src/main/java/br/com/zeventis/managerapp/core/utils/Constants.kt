package br.com.zeventis.managerapp.core.utils

internal class Constants private constructor() {

    internal object Router {
        const val AUTHENTICATION = "/authentication"
        const val REGISTER = "/producer/register"
        const val DOUBLE_CHECK_PROMOTER_EVENT = "/event/{eventId}/promoter/{promoterId}/status"
        const val REQUEST_PROMOTER_TO_EVENT = "/event/{eventId}/promoter/{promoterId}"
        const val UPDATE_EVENT = "/event/{eventId}"
        const val GET_EVENT_DETAIL = "/event/{eventId}"
        const val ADD_EVENT = "/event"
        const val GET_EVENTS_HOME = "/event/producer"
        const val UPDATE_PROFILE = "/producer"
        const val GET_PROFILE = "/producer"
        const val GET_COMPANY_BY_NAME = "/company/{name}"
    }

    internal object SharedPreferences {
        const val USER = "user"
        const val REGISTER = "register"
    }
}


