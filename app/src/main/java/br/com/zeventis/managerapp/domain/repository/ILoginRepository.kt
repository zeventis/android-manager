package br.com.zeventis.managerapp.domain.repository

import br.com.zeventis.managerapp.data.model.LoginRequest
import br.com.zeventis.managerapp.data.model.UserResponse

interface ILoginRepository {
    suspend fun doLogin(loginRequest: LoginRequest): UserResponse
}