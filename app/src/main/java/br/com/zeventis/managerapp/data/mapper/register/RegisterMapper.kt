package br.com.zeventis.managerapp.data.mapper.register

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.data.model.register.RegisterRequest
import br.com.zeventis.managerapp.domain.model.register.RegisterModel

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
        username = s.username,
        gender = s.gender,
        company = CompanyRegisterMapper.transformTo(s.company)
    )
}
