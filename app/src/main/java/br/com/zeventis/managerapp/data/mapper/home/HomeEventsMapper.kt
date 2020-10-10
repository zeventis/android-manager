package br.com.zeventis.managerapp.data.mapper.home

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.data.model.home.HomeEventsResponse
import br.com.zeventis.managerapp.domain.model.home.HomeEventsModel

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
