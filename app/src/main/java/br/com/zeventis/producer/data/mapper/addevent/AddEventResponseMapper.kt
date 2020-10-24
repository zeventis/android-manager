package br.com.zeventis.producer.data.mapper.addevent

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.data.model.addevent.AddEventResponse
import br.com.zeventis.producer.domain.model.addevent.AddEventResponseModel

object AddEventResponseMapper : BaseMapper<AddEventResponse, AddEventResponseModel>() {

    override fun transformFrom(s: AddEventResponseModel): AddEventResponse = AddEventResponse(
        id = s.id,
        name = s.name,
        date = s.date,
        imageUrl = s.imageUrl,
        promotersNumber = s.promotersNumber,
        defaultActionsPerPromoter = s.defaultActionsPerPromoter,
        minimumActionsRequiredPerPromoter = s.minimumActionsRequiredPerPromoter,
        eventCode = s.eventCode
    )

    override fun transformTo(s: AddEventResponse): AddEventResponseModel = AddEventResponseModel(
        id = s.id,
        name = s.name,
        date = s.date,
        imageUrl = s.imageUrl,
        promotersNumber = s.promotersNumber,
        defaultActionsPerPromoter = s.defaultActionsPerPromoter,
        minimumActionsRequiredPerPromoter = s.minimumActionsRequiredPerPromoter,
        eventCode = s.eventCode
    )
}
