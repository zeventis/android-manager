package br.com.zeventis.producer.data.repository

import br.com.zeventis.producer.data.api.IApiCore
import br.com.zeventis.producer.data.model.home.HomeEventsResponse
import br.com.zeventis.producer.domain.repository.IHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.standalone.KoinComponent

class HomeRepository(private val apiCore: IApiCore) : IHomeRepository, KoinComponent {

    override suspend fun getEventsHome(): List<HomeEventsResponse> =
        withContext(Dispatchers.IO) {
            apiCore.getEventsHome()
        }
}