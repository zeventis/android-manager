package br.com.zeventis.producer.domain.repository

import br.com.zeventis.producer.data.model.addevent.AddEventRequest
import br.com.zeventis.producer.data.model.addevent.AddEventResponse

interface IAddEventRepository {
    suspend fun addEvent(addEventRequest: AddEventRequest): AddEventResponse
}