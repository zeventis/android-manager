package br.com.zeventis.producer.presentation.mapper.login

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.domain.model.authentication.CompanyLoginModel
import br.com.zeventis.producer.presentation.model.authentication.Company

object CompanyLoginMapper : BaseMapper<Company, CompanyLoginModel>() {
    override fun transformFrom(s: CompanyLoginModel): Company = Company(
        name = s.name,
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )


    override fun transformTo(s: Company): CompanyLoginModel = CompanyLoginModel(
        name = s.name,
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )
}
