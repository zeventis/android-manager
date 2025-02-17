package br.com.zeventis.producer.data.mapper.home

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.data.model.home.HomeEventResponse
import br.com.zeventis.producer.domain.model.home.HomeEventModel

object HomeEventMapper : BaseMapper<HomeEventResponse, HomeEventModel>() {

    override fun transformFrom(s: HomeEventModel): HomeEventResponse = HomeEventResponse(
        id = s.id,
        name = s.name,
        date = s.date,
        imageUrl = s.imageUrl,
        sumTicketPrice = s.sumTicketPrice,
        percentProgress = s.percentProgress,
        promotersNumber = s.promotersNumber
    )

    override fun transformTo(s: HomeEventResponse): HomeEventModel = HomeEventModel(
    id = s.id,
    name = s.name,
    date = s.date,
    imageUrl = s.imageUrl,
    sumTicketPrice = s.sumTicketPrice,
    percentProgress = s.percentProgress,
    promotersNumber = s.promotersNumber
    )
}
