package br.com.zeventis.managerapp.presentation.mapper.register

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.domain.model.register.RegisterModel
import br.com.zeventis.managerapp.presentation.model.register.Register

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
        username = s.username,
        company = CompanyRegisterMapper.transformFrom(s.company)
    )

    override fun transformTo(s: Register): RegisterModel = RegisterModel(
        name = s.name,
        city = s.city,
        email = s.email,
        birthdayDate = s.birthdayDate,
        password = s.password,
        phone = s.phone,
        profile = s.profile,
        state = s.state,
        username = s.username,
        company = CompanyRegisterMapper.transformTo(s.company)
    )
}
