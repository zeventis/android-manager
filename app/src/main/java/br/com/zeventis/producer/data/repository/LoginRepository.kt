package br.com.zeventis.producer.data.repository

import br.com.zeventis.producer.data.api.IApiCore
import br.com.zeventis.producer.data.model.authentication.LoginRequest
import br.com.zeventis.producer.data.model.authentication.UserResponse
import br.com.zeventis.producer.domain.repository.ILoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.standalone.KoinComponent

class LoginRepository(private val apiCore: IApiCore) : ILoginRepository, KoinComponent {

    override suspend fun doLogin(loginRequest: LoginRequest): UserResponse =
        withContext(Dispatchers.IO) {
            apiCore.doLogin(loginRequest)
        }
}