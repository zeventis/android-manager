package br.com.zeventis.managerapp.presentation.mapper.register

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.domain.model.register.CompanyRegisterSearchModel
import br.com.zeventis.managerapp.presentation.model.register.CompanyRegisterSearchPresentation

object CompanySearchRegisterMapper :
    BaseMapper<CompanyRegisterSearchPresentation, CompanyRegisterSearchModel>() {
    override fun transformFrom(s: CompanyRegisterSearchModel) =
        CompanyRegisterSearchPresentation(
            addressNumber = s.addressNumber,
            addressComplement = s.addressComplement,
            cep = s.cep,
            name = s.name,
            phone = s.phone
        )

    override fun transformTo(s: CompanyRegisterSearchPresentation) =
        CompanyRegisterSearchModel(
            addressNumber = s.addressNumber,
            addressComplement = s.addressComplement,
            cep = s.cep,
            name = s.name,
            phone = s.phone
        )
}
