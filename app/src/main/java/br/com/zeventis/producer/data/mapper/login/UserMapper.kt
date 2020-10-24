package br.com.zeventis.producer.data.mapper.login

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.data.model.authentication.UserResponse
import br.com.zeventis.producer.domain.model.authentication.UserModel

object UserMapper : BaseMapper<UserResponse, UserModel>() {
    override fun transformFrom(s: UserModel): UserResponse = UserResponse(
        name = s.name,
        token = s.token,
        username = s.username,
        email = s.email,
        birthdayDate = s.birthdayDate,
        phone = s.phone,
        city = s.city,
        company = CompanyMapper.transformFrom(s.company),
        state = s.state,
        profile = s.profile,
        gender = s.gender,
        instagram = s.instagram ?: ""
    )

    override fun transformTo(s: UserResponse): UserModel = UserModel(
        name = s.name,
        token = s.token,
        username = s.username,
        email = s.email,
        birthdayDate = s.birthdayDate,
        phone = s.phone,
        city = s.city,
        company = CompanyMapper.transformTo(s.company),
        state = s.state,
        instagram = s.instagram ?: "",
        gender = s.gender,
        profile = s.profile,
    )
}
