package br.com.zeventis.managerapp.presentation.mapper.login

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.domain.model.authentication.UserModel
import br.com.zeventis.managerapp.presentation.model.login.User

object UserMapper : BaseMapper<User, UserModel>() {
    override fun transformFrom(s: UserModel): User = User(
        name = s.name,
        token = s.token,
        username = s.username,
        email = s.email,
        birthdayDate = s.birthdayDate,
        phone = s.phone,
        city = s.city,
        company = CompanyLoginMapper.transformFrom(s.company),
        state = s.state
    )

    override fun transformTo(s: User): UserModel = UserModel(
        name = s.name,
        token = s.token,
        username = s.username,
        email = s.email,
        birthdayDate = s.birthdayDate,
        phone = s.phone,
        city = s.city,
        company = CompanyLoginMapper.transformTo(s.company),
        state = s.state
    )
}
