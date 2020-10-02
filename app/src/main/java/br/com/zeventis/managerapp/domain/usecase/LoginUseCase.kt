package br.com.zeventis.managerapp.domain.usecase

import br.com.zeventis.managerapp.data.mapper.login.LoginMapper
import br.com.zeventis.managerapp.data.mapper.login.UserMapper
import br.com.zeventis.managerapp.domain.model.LoginModel
import br.com.zeventis.managerapp.domain.model.UserModel
import br.com.zeventis.managerapp.domain.repository.ILoginRepository

open class LoginUseCase(private val loginRepository: ILoginRepository) {
    suspend fun execute(login: LoginModel): UserModel =
        UserMapper.transformTo(loginRepository.doLogin(LoginMapper.transformFrom(login)))
}