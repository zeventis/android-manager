package br.com.zeventis.managerapp.data.mapper.login

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.data.model.authentication.LoginRequest
import br.com.zeventis.managerapp.domain.model.authentication.LoginModel

object LoginMapper : BaseMapper<LoginRequest, LoginModel>() {
    override fun transformFrom(s: LoginModel): LoginRequest = LoginRequest(
        username = s.username,
        password = s.password
    )

    override fun transformTo(s: LoginRequest): LoginModel = LoginModel(
        username = s.username,
        password = s.password
    )
}
