package br.com.zeventis.managerapp.data.mapper.addevent

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.data.model.addevent.AddEventRequest
import br.com.zeventis.managerapp.domain.model.addevent.AddEventRequestModel

object AddEventRequestMapper : BaseMapper<AddEventRequest, AddEventRequestModel>() {

    override fun transformFrom(s: AddEventRequestModel): AddEventRequest = AddEventRequest(
        name = s.name,
        date = s.date,
        base64Image = s.base64Image,
        defaultActionsPerPromoter = s.defaultActionsPerPromoter,
        ticketPrice = s.ticketPrice,
        minimumActionsRequiredPerPromoter = s.minimumActionsRequiredPerPromoter
    )

    override fun transformTo(s: AddEventRequest): AddEventRequestModel = AddEventRequestModel(
        name = s.name,
        date = s.date,
        base64Image = s.base64Image,
        defaultActionsPerPromoter = s.defaultActionsPerPromoter,
        ticketPrice = s.ticketPrice,
        minimumActionsRequiredPerPromoter = s.minimumActionsRequiredPerPromoter
    )
}
