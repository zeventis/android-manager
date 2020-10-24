package br.com.zeventis.producer.data.repository

import br.com.zeventis.producer.data.api.IApiCore
import br.com.zeventis.producer.data.model.authentication.UserResponse
import br.com.zeventis.producer.data.model.register.CompanyRegisterSearchResponse
import br.com.zeventis.producer.data.model.register.RegisterRequest
import br.com.zeventis.producer.domain.repository.IRegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.standalone.KoinComponent

class RegisterRepository(private val apiCore: IApiCore) : IRegisterRepository, KoinComponent {

    override suspend fun register(registerRequest: RegisterRequest): UserResponse =
        withContext(Dispatchers.IO) {
            apiCore.register(registerRequest)
        }

    override suspend fun getCompany(companyName: String): List<CompanyRegisterSearchResponse> =
        withContext(Dispatchers.IO) {
            apiCore.getCompanyByName(companyName)
        }

}