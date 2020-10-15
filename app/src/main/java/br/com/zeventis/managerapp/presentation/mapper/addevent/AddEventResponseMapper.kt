package br.com.zeventis.managerapp.presentation.mapper.addevent

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.domain.model.addevent.AddEventResponseModel
import br.com.zeventis.managerapp.presentation.model.addevent.AddEventResponsePresentation

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
