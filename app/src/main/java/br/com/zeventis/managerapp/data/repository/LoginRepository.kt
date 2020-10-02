package br.com.zeventis.managerapp.data.repository

import android.content.Context
import br.com.zeventis.managerapp.core.utils.Constants
import br.com.zeventis.managerapp.core.utils.JsonUtils
import br.com.zeventis.managerapp.core.utils.MockUtils
import br.com.zeventis.managerapp.data.model.LoginRequest
import br.com.zeventis.managerapp.data.model.UserResponse
import br.com.zeventis.managerapp.domain.repository.ILoginRepository

class LoginRepository(private val context: Context) : ILoginRepository {

    override suspend fun doLogin(loginRequest: LoginRequest): UserResponse {
        val filename = Constants.JsonFileMock.LOGIN_JSON
        val jsonString: String

        jsonString = MockUtils().openStringFile(context, filename)
        return JsonUtils.gson.fromJson(jsonString, UserResponse::class.java)
    }
}