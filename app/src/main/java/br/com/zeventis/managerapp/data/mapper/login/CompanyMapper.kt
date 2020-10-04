package br.com.zeventis.managerapp.data.mapper.login

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.data.model.authentication.CompanyResponse
import br.com.zeventis.managerapp.domain.model.authentication.CompanyModel

object CompanyMapper : BaseMapper<CompanyResponse, CompanyModel>() {
    override fun transformFrom(s: CompanyModel): CompanyResponse = CompanyResponse(
        name = s.name,
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )


    override fun transformTo(s: CompanyResponse): CompanyModel = CompanyModel(
        name = s.name,
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )
}
