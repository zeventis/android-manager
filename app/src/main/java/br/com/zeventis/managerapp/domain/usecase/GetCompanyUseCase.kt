package br.com.zeventis.managerapp.domain.usecase

import br.com.zeventis.managerapp.data.mapper.register.CompanySearchRegisterMapper
import br.com.zeventis.managerapp.domain.model.register.CompanyRegisterSearchModel
import br.com.zeventis.managerapp.domain.repository.IRegisterRepository

open class GetCompanyUseCase(private val registerRepository: IRegisterRepository) {
    suspend fun execute(companyName: String): List<CompanyRegisterSearchModel> =
        CompanySearchRegisterMapper.transformToList(registerRepository.getCompany(companyName))
}