package br.com.zeventis.managerapp.presentation.mapper.register

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.domain.model.register.CompanyRegisterModel
import br.com.zeventis.managerapp.presentation.model.register.Company

object CompanyRegisterMapper : BaseMapper<Company, CompanyRegisterModel>() {
    override fun transformFrom(s: CompanyRegisterModel): Company = Company(
        name = s.name,
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )


    override fun transformTo(s: Company): CompanyRegisterModel = CompanyRegisterModel(
        name = s.name,
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )
}
