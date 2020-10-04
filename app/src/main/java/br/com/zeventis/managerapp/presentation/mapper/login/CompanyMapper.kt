package br.com.zeventis.managerapp.presentation.mapper.login

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.domain.model.authentication.CompanyModel
import br.com.zeventis.managerapp.presentation.model.Company

object CompanyMapper : BaseMapper<Company, CompanyModel>() {
    override fun transformFrom(s: CompanyModel): Company = Company(
        name = s.name,
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )


    override fun transformTo(s: Company): CompanyModel = CompanyModel(
        name = s.name,
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )
}
