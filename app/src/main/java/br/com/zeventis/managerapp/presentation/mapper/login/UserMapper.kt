package br.com.zeventis.managerapp.presentation.mapper.login

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.domain.model.UserModel
import br.com.zeventis.managerapp.presentation.model.User

object UserMapper : BaseMapper<User, UserModel>() {
    override fun transformFrom(s: UserModel): User = User(
        id = s.id,
        name = s.name,
        token = s.token
    )

    override fun transformTo(s: User): UserModel = UserModel(
        id = s.id,
        name = s.name,
        token = s.token
    )
}
