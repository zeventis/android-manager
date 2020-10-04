package br.com.zeventis.managerapp.data.api

import br.com.zeventis.managerapp.core.utils.Constants
import br.com.zeventis.managerapp.data.model.authentication.LoginRequest
import br.com.zeventis.managerapp.data.model.authentication.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface IApiCore {
    @POST(Constants.Router.AUTHENTICATION)
    suspend fun doLogin(@Body loginRequest: LoginRequest): UserResponse
}