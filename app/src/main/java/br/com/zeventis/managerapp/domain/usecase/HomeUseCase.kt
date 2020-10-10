package br.com.zeventis.managerapp.domain.usecase

import br.com.zeventis.managerapp.data.mapper.home.HomeEventsMapper
import br.com.zeventis.managerapp.domain.model.home.HomeEventsModel
import br.com.zeventis.managerapp.domain.repository.IHomeRepository

open class HomeUseCase(private val homeRepository: IHomeRepository) {
    suspend fun execute(): List<HomeEventsModel> =
        HomeEventsMapper.transformToList(homeRepository.getEventsHome())
}