package br.com.zeventis.producer.domain.repository

import br.com.zeventis.producer.data.model.home.HomeEventsResponse

interface IHomeRepository {
    suspend fun getEventsHome(): List<HomeEventsResponse>
}