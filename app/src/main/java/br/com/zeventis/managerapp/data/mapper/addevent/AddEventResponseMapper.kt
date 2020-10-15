package br.com.zeventis.managerapp.data.mapper.addevent

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.data.model.addevent.AddEventResponse
import br.com.zeventis.managerapp.domain.model.addevent.AddEventResponseModel

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
