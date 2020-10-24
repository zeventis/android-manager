package br.com.zeventis.producer.domain.usecase

import br.com.zeventis.producer.data.mapper.login.UserMapper
import br.com.zeventis.producer.data.mapper.register.RegisterMapper
import br.com.zeventis.producer.domain.model.authentication.UserModel
import br.com.zeventis.producer.domain.model.register.RegisterModel
import br.com.zeventis.producer.domain.repository.IRegisterRepository

open class RegisterUseCase(private val registerRepository: IRegisterRepository) {
    suspend fun execute(register: RegisterModel): UserModel =
        UserMapper.transformTo(registerRepository.register(RegisterMapper.transformFrom(register)))
}