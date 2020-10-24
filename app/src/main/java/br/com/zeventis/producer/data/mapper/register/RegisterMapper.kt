package br.com.zeventis.producer.data.mapper.register

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.data.model.register.RegisterRequest
import br.com.zeventis.producer.domain.model.register.RegisterModel

object RegisterMapper : BaseMapper<RegisterRequest, RegisterModel>() {
    override fun transformFrom(s: RegisterModel): RegisterRequest = RegisterRequest(
        name = s.name,
        city = s.city,
        email = s.email,
        birthdayDate = s.birthdayDate,
        password = s.password,
        phone = s.phone,
        profile = s.profile,
        state = s.state,
        username = s.username,
        instagram = s.instagram ?: "",
        gender = s.gender,
        company = CompanyRegisterMapper.transformFrom(s.company)
    )

    override fun transformTo(s: RegisterRequest): RegisterModel = RegisterModel(
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
        company = CompanyRegisterMapper.transformTo(s.company)
    )
}
