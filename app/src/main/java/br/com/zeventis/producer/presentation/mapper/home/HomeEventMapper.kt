package br.com.zeventis.producer.presentation.mapper.home

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.domain.model.home.HomeEventModel
import br.com.zeventis.producer.presentation.model.home.HomeEvent

object HomeEventMapper : BaseMapper<HomeEventModel, HomeEvent>() {

    override fun transformFrom(s: HomeEvent): HomeEventModel = HomeEventModel(
        id = s.id,
        name = s.name,
        date = s.date,
        imageUrl = s.imageUrl,
        sumTicketPrice = s.sumTicketPrice,
        percentProgress = s.percentProgress,
        promotersNumber = s.promotersNumber
    )

    override fun transformTo(s: HomeEventModel): HomeEvent = HomeEvent(
        id = s.id,
        name = s.name,
        date = s.date,
        imageUrl = s.imageUrl,
        sumTicketPrice = s.sumTicketPrice,
        percentProgress = s.percentProgress,
        promotersNumber = s.promotersNumber
    )
}
