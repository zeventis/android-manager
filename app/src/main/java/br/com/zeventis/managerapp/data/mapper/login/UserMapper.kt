package br.com.zeventis.managerapp.data.mapper.login

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.data.model.UserResponse
import br.com.zeventis.managerapp.domain.model.UserModel

object UserMapper : BaseMapper<UserResponse, UserModel>() {
    override fun transformFrom(s: UserModel): UserResponse = UserResponse(
        id = s.id,
        name = s.name,
        token = s.token
    )

    override fun transformTo(s: UserResponse): UserModel = UserModel(
        id = s.id,
        name = s.name,
        token = s.token
    )
}
