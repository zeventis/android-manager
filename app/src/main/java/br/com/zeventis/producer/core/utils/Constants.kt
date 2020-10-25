package br.com.zeventis.producer.core.utils

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

    internal object Mask {
        const val DATE = "[00]{/}[00]{/}[0000]"
        const val HOUR = "[00]{:}[00]"
        const val MONEY = "R$ [00999]"
        const val PHONE = "([00]) [0] [0000]{-}[0000]"
        const val CEP = "[0000]{-}[00]"
    }

    internal object MaskDigits {
        const val DATE = "/0123456789"
        const val HOUR = ":0123456789"
        const val MONEY = "R$ 0123456789"
        const val PHONE = "()-0123456789 "
        const val CEP = "-0123456789"
    }
}


