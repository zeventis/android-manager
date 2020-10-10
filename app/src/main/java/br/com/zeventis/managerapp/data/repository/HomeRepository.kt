package br.com.zeventis.managerapp.data.repository

import br.com.zeventis.managerapp.data.api.IApiCore
import br.com.zeventis.managerapp.data.model.home.HomeEventsResponse
import br.com.zeventis.managerapp.domain.repository.IHomeRepository
import org.koin.standalone.KoinComponent

class HomeRepository(private val apiCore: IApiCore) : IHomeRepository, KoinComponent {

    override suspend fun getEventsHome(): List<HomeEventsResponse> = apiCore.getEventsHome()
}