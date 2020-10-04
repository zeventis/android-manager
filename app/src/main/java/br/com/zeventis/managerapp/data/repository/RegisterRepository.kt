package br.com.zeventis.managerapp.data.repository

import br.com.zeventis.managerapp.data.api.IApiCore
import br.com.zeventis.managerapp.data.model.authentication.UserResponse
import br.com.zeventis.managerapp.data.model.register.RegisterRequest
import br.com.zeventis.managerapp.domain.repository.IRegisterRepository
import org.koin.standalone.KoinComponent

class RegisterRepository(private val apiCore: IApiCore) : IRegisterRepository, KoinComponent {

    override suspend fun register(registerRequest: RegisterRequest): UserResponse =
        apiCore.register(registerRequest)
}