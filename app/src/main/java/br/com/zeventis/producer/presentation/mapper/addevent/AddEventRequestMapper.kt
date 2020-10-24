package br.com.zeventis.producer.presentation.mapper.addevent

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.domain.model.addevent.AddEventRequestModel
import br.com.zeventis.producer.presentation.model.addevent.AddEventRequestPresentation

object AddEventRequestMapper : BaseMapper<AddEventRequestModel, AddEventRequestPresentation>() {

    override fun transformFrom(s: AddEventRequestPresentation): AddEventRequestModel =
        AddEventRequestModel(
            name = s.name,
            date = s.date,
            base64Image = s.base64Image,
            defaultActionsPerPromoter = s.defaultActionsPerPromoter,
            ticketPrice = s.ticketPrice,
            minimumActionsRequiredPerPromoter = s.minimumActionsRequiredPerPromoter
        )

    override fun transformTo(s: AddEventRequestModel): AddEventRequestPresentation =
        AddEventRequestPresentation(
            name = s.name,
            date = s.date,
            base64Image = s.base64Image,
            defaultActionsPerPromoter = s.defaultActionsPerPromoter,
            ticketPrice = s.ticketPrice,
            minimumActionsRequiredPerPromoter = s.minimumActionsRequiredPerPromoter
        )
}
