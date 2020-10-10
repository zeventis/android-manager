package br.com.zeventis.managerapp.presentation.mapper.home

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.domain.model.home.HomeEventsModel
import br.com.zeventis.managerapp.presentation.model.home.HomeEvents

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
