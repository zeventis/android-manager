package br.com.zeventis.producer.presentation.mapper.register

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.domain.model.register.CompanyRegisterModel
import br.com.zeventis.producer.presentation.model.register.Company

object CompanyRegisterMapper : BaseMapper<Company?, CompanyRegisterModel?>() {
    override fun transformFrom(s: CompanyRegisterModel?): Company? = s?.let {
        Company(
            name = s.name,
            cep = s.cep,
            phone = s.phone,
            addressComplement = s.addressComplement,
            addressNumber = s.addressNumber
        )
    }

    override fun transformTo(s: Company?): CompanyRegisterModel? = s?.let {
        CompanyRegisterModel(
            name = s.name,
            cep = s.cep,
            phone = s.phone,
            addressComplement = s.addressComplement,
            addressNumber = s.addressNumber
        )
    }
}
