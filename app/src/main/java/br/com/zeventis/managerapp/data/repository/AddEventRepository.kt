package br.com.zeventis.managerapp.data.repository

import br.com.zeventis.managerapp.data.api.IApiCore
import br.com.zeventis.managerapp.data.model.addevent.AddEventRequest
import br.com.zeventis.managerapp.data.model.addevent.AddEventResponse
import br.com.zeventis.managerapp.domain.repository.IAddEventRepository
import org.koin.standalone.KoinComponent

class AddEventRepository(private val apiCore: IApiCore) : IAddEventRepository, KoinComponent {

    override suspend fun addEvent(addEventRequest: AddEventRequest): AddEventResponse {
        val addEvent = apiCore.addEvent(addEventRequest)

        return addEvent
    }
}

