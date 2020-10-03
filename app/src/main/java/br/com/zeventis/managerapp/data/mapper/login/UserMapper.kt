package br.com.zeventis.managerapp.data.mapper.login

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.data.model.authentication.UserResponse
import br.com.zeventis.managerapp.domain.model.authentication.UserModel

object UserMapper : BaseMapper<UserResponse, UserModel>() {
    override fun transformFrom(s: UserModel): UserResponse = UserResponse(
        name = s.name,
        token = s.token,
        username = s.username,
        email = s.email,
        birthdayDate = s.birthdayDate,
        phone = s.phone,
        city = s.city,
        producer = ProducerMapper.transformFrom(s.producer),
        state = s.state
    )

    override fun transformTo(s: UserResponse): UserModel = UserModel(
        name = s.name,
        token = s.token,
        username = s.username,
        email = s.email,
        birthdayDate = s.birthdayDate,
        phone = s.phone,
        city = s.city,
        producer = ProducerMapper.transformTo(s.producer),
        state = s.state
    )
}
