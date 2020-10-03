package br.com.zeventis.managerapp.data.repository

import br.com.zeventis.managerapp.data.api.IApiCore
import br.com.zeventis.managerapp.data.model.authentication.LoginRequest
import br.com.zeventis.managerapp.data.model.authentication.UserResponse
import br.com.zeventis.managerapp.domain.repository.ILoginRepository
import org.koin.standalone.KoinComponent

class LoginRepository(private val apiCore: IApiCore) : ILoginRepository, KoinComponent {

    override suspend fun doLogin(loginRequest: LoginRequest): UserResponse =
        apiCore.doLogin(loginRequest)
}