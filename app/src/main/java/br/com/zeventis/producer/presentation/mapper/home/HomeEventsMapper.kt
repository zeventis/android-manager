package br.com.zeventis.producer.presentation.mapper.home

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.domain.model.home.HomeEventsModel
import br.com.zeventis.producer.presentation.model.home.HomeEvents

object HomeEventsMapper : BaseMapper<HomeEventsModel, HomeEvents>() {

    override fun transformTo(s: HomeEventsModel): HomeEvents = HomeEvents(
        date = s.date,
        events = HomeEventMapper.transformToList(s.events)
    )

    override fun transformFrom(s: HomeEvents): HomeEventsModel = HomeEventsModel(
        date = s.date,
        events = HomeEventMapper.transformFromList(s.events)
    )
}
