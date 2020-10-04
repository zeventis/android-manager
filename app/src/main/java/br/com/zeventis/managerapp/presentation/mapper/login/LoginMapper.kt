package br.com.zeventis.managerapp.presentation.mapper.login

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.domain.model.authentication.LoginModel
import br.com.zeventis.managerapp.presentation.model.login.Login

object LoginMapper : BaseMapper<LoginModel, Login>() {
    override fun transformFrom(s: Login): LoginModel = LoginModel(
        username = s.username,
        password = s.password
    )

    override fun transformTo(s: LoginModel): Login = Login(
        username = s.username,
        password = s.password
    )
}
