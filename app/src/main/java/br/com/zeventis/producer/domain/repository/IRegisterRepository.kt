package br.com.zeventis.producer.domain.repository

import br.com.zeventis.producer.data.model.authentication.UserResponse
import br.com.zeventis.producer.data.model.register.CompanyRegisterSearchResponse
import br.com.zeventis.producer.data.model.register.RegisterRequest

interface IRegisterRepository {
    suspend fun register(registerRequest: RegisterRequest): UserResponse
    suspend fun getCompany(companyName: String): List<CompanyRegisterSearchResponse>
}