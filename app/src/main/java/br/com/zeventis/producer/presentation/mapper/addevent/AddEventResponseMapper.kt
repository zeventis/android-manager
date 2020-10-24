package br.com.zeventis.producer.presentation.mapper.addevent

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.domain.model.addevent.AddEventResponseModel
import br.com.zeventis.producer.presentation.model.addevent.AddEventResponsePresentation

object AddEventResponseMapper : BaseMapper<AddEventResponseModel, AddEventResponsePresentation>() {

    override fun transformFrom(s: AddEventResponsePresentation): AddEventResponseModel =
        AddEventResponseModel(
            id = s.id,
            name = s.name,
            date = s.date,
            imageUrl = s.imageUrl,
            promotersNumber = s.promotersNumber,
            defaultActionsPerPromoter = s.defaultActionsPerPromoter,
            minimumActionsRequiredPerPromoter = s.minimumActionsRequiredPerPromoter,
            eventCode = s.eventCode
        )

    override fun transformTo(s: AddEventResponseModel): AddEventResponsePresentation =
        AddEventResponsePresentation(
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
