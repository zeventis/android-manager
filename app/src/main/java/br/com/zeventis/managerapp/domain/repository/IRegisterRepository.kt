package br.com.zeventis.managerapp.domain.repository

import br.com.zeventis.managerapp.data.model.authentication.UserResponse
import br.com.zeventis.managerapp.data.model.register.RegisterRequest

interface IRegisterRepository {
    suspend fun register(registerRequest: RegisterRequest): UserResponse
}