package br.com.zeventis.producer.presentation.mapper.login

import br.com.zeventis.producer.core.plataform.BaseMapper
import br.com.zeventis.producer.domain.model.authentication.LoginModel
import br.com.zeventis.producer.presentation.model.authentication.Login

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
