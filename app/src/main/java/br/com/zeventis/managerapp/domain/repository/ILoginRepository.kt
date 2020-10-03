package br.com.zeventis.managerapp.domain.repository

import br.com.zeventis.managerapp.data.model.authentication.LoginRequest
import br.com.zeventis.managerapp.data.model.authentication.UserResponse

interface ILoginRepository {
    suspend fun doLogin(loginRequest: LoginRequest): UserResponse
}