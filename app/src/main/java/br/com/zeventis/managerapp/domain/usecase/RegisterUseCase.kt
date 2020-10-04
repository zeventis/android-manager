package br.com.zeventis.managerapp.domain.usecase

import br.com.zeventis.managerapp.data.mapper.login.UserMapper
import br.com.zeventis.managerapp.data.mapper.register.RegisterMapper
import br.com.zeventis.managerapp.domain.model.authentication.UserModel
import br.com.zeventis.managerapp.domain.model.register.RegisterModel
import br.com.zeventis.managerapp.domain.repository.IRegisterRepository

open class RegisterUseCase(private val registerRepository: IRegisterRepository) {
    suspend fun execute(register: RegisterModel): UserModel =
        UserMapper.transformTo(registerRepository.register(RegisterMapper.transformFrom(register)))
}