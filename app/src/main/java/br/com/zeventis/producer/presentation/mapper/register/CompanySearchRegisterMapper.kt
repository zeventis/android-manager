package br.com.zeventis.producer.presentation.mapper.register

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.domain.model.register.CompanyRegisterSearchModel
import br.com.zeventis.producer.presentation.model.register.CompanyRegisterSearchPresentation

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
