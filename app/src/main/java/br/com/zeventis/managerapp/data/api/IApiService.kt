package br.com.zeventis.managerapp.data.api

import br.com.zeventis.managerapp.data.model.LoginRequest
import br.com.zeventis.managerapp.data.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface IApiService {
    @POST("/login")
    suspend fun doLogin(@Body loginRequest: LoginRequest): UserResponse
}