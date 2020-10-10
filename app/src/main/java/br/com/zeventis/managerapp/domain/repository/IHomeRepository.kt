package br.com.zeventis.managerapp.domain.repository

import br.com.zeventis.managerapp.data.model.home.HomeEventsResponse

interface IHomeRepository {
    suspend fun getEventsHome(): List<HomeEventsResponse>
}