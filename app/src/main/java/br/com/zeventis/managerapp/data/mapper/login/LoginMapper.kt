package br.com.zeventis.managerapp.data.mapper.login

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.data.model.LoginRequest
import br.com.zeventis.managerapp.domain.model.LoginModel

object LoginMapper : BaseMapper<LoginRequest, LoginModel>() {
    override fun transformFrom(s: LoginModel): LoginRequest = LoginRequest(
        login = s.login,
        password = s.password
    )

    override fun transformTo(s: LoginRequest): LoginModel = LoginModel(
        login = s.login,
        password = s.password
    )
}
