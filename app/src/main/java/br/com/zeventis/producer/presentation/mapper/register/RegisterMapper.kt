package br.com.zeventis.producer.presentation.mapper.register

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.domain.model.register.RegisterModel
import br.com.zeventis.producer.presentation.model.register.Register

object RegisterMapper : BaseMapper<Register, RegisterModel>() {
    override fun transformFrom(s: RegisterModel): Register = Register(
        name = s.name,
        city = s.city,
        email = s.email,
        birthdayDate = s.birthdayDate,
        password = s.password,
        phone = s.phone,
        profile = s.profile,
        state = s.state,
        instagram = s.instagram ?: "",
        username = s.username,
        gender = s.gender,
        company = CompanyRegisterMapper.transformFrom(s.company)
    )

    override fun transformTo(s: Register): RegisterModel = RegisterModel(
        name = s.name,
        city = s.city,
        email = s.email,
        birthdayDate = s.birthdayDate,
        password = s.password,
        instagram = s.instagram ?: "",
        phone = s.phone,
        profile = s.profile,
        state = s.state,
        username = s.username,
        gender = s.gender,
        company = CompanyRegisterMapper.transformTo(s.company)
    )
}
