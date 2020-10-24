package br.com.zeventis.producer.data.repository

import br.com.zeventis.producer.data.api.IApiCore
import br.com.zeventis.producer.data.model.addevent.AddEventRequest
import br.com.zeventis.producer.data.model.addevent.AddEventResponse
import br.com.zeventis.producer.domain.repository.IAddEventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.standalone.KoinComponent

class AddEventRepository(private val apiCore: IApiCore) : IAddEventRepository, KoinComponent {

    override suspend fun addEvent(addEventRequest: AddEventRequest): AddEventResponse =
        withContext(Dispatchers.IO) {
            apiCore.addEvent(addEventRequest)
        }
}

