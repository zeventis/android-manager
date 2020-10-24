package br.com.zeventis.producer.domain.usecase

import br.com.zeventis.producer.data.mapper.addevent.AddEventRequestMapper
import br.com.zeventis.producer.data.mapper.addevent.AddEventResponseMapper
import br.com.zeventis.producer.domain.model.addevent.AddEventRequestModel
import br.com.zeventis.producer.domain.model.addevent.AddEventResponseModel
import br.com.zeventis.producer.domain.repository.IAddEventRepository

open class AddEventUseCase(private val addEventRepository: IAddEventRepository) {
    suspend fun execute(addEventRequestModel: AddEventRequestModel): AddEventResponseModel =
        AddEventResponseMapper.transformTo(
            addEventRepository.addEvent(
                AddEventRequestMapper.transformFrom(
                    addEventRequestModel
                )
            )
        )
}