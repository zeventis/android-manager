package br.com.zeventis.producer.domain.repository

import br.com.zeventis.producer.data.model.authentication.LoginRequest
import br.com.zeventis.producer.data.model.authentication.UserResponse

interface ILoginRepository {
    suspend fun doLogin(loginRequest: LoginRequest): UserResponse
}