package br.com.zeventis.producer.domain.usecase

import br.com.zeventis.producer.data.mapper.home.HomeEventsMapper
import br.com.zeventis.producer.domain.model.home.HomeEventsModel
import br.com.zeventis.producer.domain.repository.IHomeRepository

open class HomeUseCase(private val homeRepository: IHomeRepository) {
    suspend fun execute(): List<HomeEventsModel> =
        HomeEventsMapper.transformToList(homeRepository.getEventsHome())
}