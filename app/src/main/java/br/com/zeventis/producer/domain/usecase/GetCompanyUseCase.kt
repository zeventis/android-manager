package br.com.zeventis.producer.domain.usecase

import br.com.zeventis.producer.data.mapper.register.CompanySearchRegisterMapper
import br.com.zeventis.producer.domain.model.register.CompanyRegisterSearchModel
import br.com.zeventis.producer.domain.repository.IRegisterRepository

open class GetCompanyUseCase(private val registerRepository: IRegisterRepository) {
    suspend fun execute(companyName: String): List<CompanyRegisterSearchModel> =
        CompanySearchRegisterMapper.transformToList(registerRepository.getCompany(companyName))
}