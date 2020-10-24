package br.com.zeventis.producer.data.mapper.home

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.data.model.home.HomeEventsResponse
import br.com.zeventis.producer.domain.model.home.HomeEventsModel

object HomeEventsMapper : BaseMapper<HomeEventsResponse, HomeEventsModel>() {

    override fun transformTo(s: HomeEventsResponse): HomeEventsModel = HomeEventsModel(
        date = s.date,
        events = HomeEventMapper.transformToList(s.events)
    )

    override fun transformFrom(s: HomeEventsModel): HomeEventsResponse = HomeEventsResponse(
        date = s.date,
        events = HomeEventMapper.transformFromList(s.events)
    )
}
