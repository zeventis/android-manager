package br.com.zeventis.managerapp.data.mapper.login

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.data.model.authentication.ProducerResponse
import br.com.zeventis.managerapp.domain.model.authentication.ProducerModel

object ProducerMapper : BaseMapper<ProducerResponse, ProducerModel>() {
    override fun transformFrom(s: ProducerModel): ProducerResponse = ProducerResponse(
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )


    override fun transformTo(s: ProducerResponse): ProducerModel = ProducerModel(
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )
}
