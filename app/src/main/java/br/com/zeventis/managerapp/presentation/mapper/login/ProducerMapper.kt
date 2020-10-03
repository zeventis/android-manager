package br.com.zeventis.managerapp.presentation.mapper.login

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.domain.model.authentication.ProducerModel
import br.com.zeventis.managerapp.presentation.model.Producer

object ProducerMapper : BaseMapper<Producer, ProducerModel>() {
    override fun transformFrom(s: ProducerModel): Producer = Producer(
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )


    override fun transformTo(s: Producer): ProducerModel = ProducerModel(
        cep = s.cep,
        phone = s.phone,
        addressComplement = s.addressComplement,
        addressNumber = s.addressNumber
    )
}
