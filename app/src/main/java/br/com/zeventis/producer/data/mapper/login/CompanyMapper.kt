package br.com.zeventis.producer.data.mapper.login

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.data.model.authentication.CompanyLoginResponse
import br.com.zeventis.producer.domain.model.authentication.CompanyLoginModel

object CompanyMapper : BaseMapper<CompanyLoginResponse, CompanyLoginModel>() {
    override fun transformFrom(s: CompanyLoginModel): CompanyLoginResponse = CompanyLoginResponse(
        name = s.name,
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )

    override fun transformTo(s: CompanyLoginResponse): CompanyLoginModel = CompanyLoginModel(
        name = s.name,
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )
}
