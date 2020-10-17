package br.com.zeventis.managerapp.data.mapper.register

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.data.model.register.CompanyRegisterSearchResponse
import br.com.zeventis.managerapp.domain.model.register.CompanyRegisterSearchModel

object CompanySearchRegisterMapper :
    BaseMapper<CompanyRegisterSearchResponse, CompanyRegisterSearchModel>() {
    override fun transformFrom(s: CompanyRegisterSearchModel) =
        CompanyRegisterSearchResponse(
            addressNumber = s.addressNumber,
            addressComplement = s.addressComplement,
            cep = s.cep,
            name = s.name,
            phone = s.phone
        )

    override fun transformTo(s: CompanyRegisterSearchResponse) =
        CompanyRegisterSearchModel(
            addressNumber = s.addressNumber,
            addressComplement = s.addressComplement,
            cep = s.cep,
            name = s.name,
            phone = s.phone
        )
}
