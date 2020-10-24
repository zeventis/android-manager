package br.com.zeventis.producer.domain.usecase

import br.com.zeventis.producer.data.mapper.login.LoginMapper
import br.com.zeventis.producer.data.mapper.login.UserMapper
import br.com.zeventis.producer.domain.model.authentication.LoginModel
import br.com.zeventis.producer.domain.model.authentication.UserModel
import br.com.zeventis.producer.domain.repository.ILoginRepository

open class LoginUseCase(private val loginRepository: ILoginRepository) {
    suspend fun execute(login: LoginModel): UserModel =
        UserMapper.transformTo(loginRepository.doLogin(LoginMapper.transformFrom(login)))
}