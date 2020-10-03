package br.com.zeventis.managerapp.presentation.mapper.login

import br.com.zeventis.managerapp.core.plataform.BaseMapper
import br.com.zeventis.managerapp.domain.model.authentication.LoginModel
import br.com.zeventis.managerapp.presentation.model.Login

object LoginMapper : BaseMapper<LoginModel, Login>() {
    override fun transformFrom(s: Login): LoginModel = LoginModel(
        user = s.user,
        password = s.password
    )

    override fun transformTo(s: LoginModel): Login = Login(
        user = s.user,
        password = s.password
    )
}
