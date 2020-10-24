package br.com.zeventis.producer.data.mapper.register

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.data.model.register.CompanyRegisterRequest
import br.com.zeventis.producer.domain.model.register.CompanyRegisterModel

object CompanyRegisterMapper : BaseMapper<CompanyRegisterRequest?, CompanyRegisterModel?>() {
    override fun transformFrom(s: CompanyRegisterModel?): CompanyRegisterRequest? =
        s?.let {
            CompanyRegisterRequest(
                name = s.name,
                cep = s.cep,
                phone = s.phone,
                addressComplement = s.addressComplement,
                addressNumber = s.addressNumber
            )
        }

    override fun transformTo(s: CompanyRegisterRequest?): CompanyRegisterModel? =
        s?.let {
            CompanyRegisterModel(
                name = s.name,
                cep = s.cep,
                phone = s.phone,
                addressComplement = s.addressComplement,
                addressNumber = s.addressNumber
            )
        }
}
