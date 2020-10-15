package br.com.zeventis.managerapp.domain.repository

import br.com.zeventis.managerapp.data.model.addevent.AddEventRequest
import br.com.zeventis.managerapp.data.model.addevent.AddEventResponse

interface IAddEventRepository {
    suspend fun addEvent(addEventRequest: AddEventRequest): AddEventResponse
}