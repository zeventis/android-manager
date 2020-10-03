package br.com.zeventis.managerapp.data.repository

import android.content.Context
import br.com.zeventis.managerapp.core.ZeventisApplication
import br.com.zeventis.managerapp.core.utils.Constants
import br.com.zeventis.managerapp.core.utils.JsonUtils
import br.com.zeventis.managerapp.core.utils.MockUtils
import br.com.zeventis.managerapp.data.api.IApiCore
import br.com.zeventis.managerapp.data.model.LoginRequest
import br.com.zeventis.managerapp.data.model.UserResponse
import br.com.zeventis.managerapp.domain.repository.ILoginRepository
import org.koin.standalone.KoinComponent

class LoginRepository(private val context: Context, private val apiCore: IApiCore) : ILoginRepository, KoinComponent {

    override suspend fun doLogin(loginRequest: LoginRequest): UserResponse {
        when {
            ZeventisApplication.isDebugHerokuVersion() -> {
                val response = apiCore.doLogin(loginRequest)
                ZeventisApplication().setUserToken(response.token)
                return response
            }
            ZeventisApplication.isDebugMockVersion() -> {
                val filename = Constants.JsonFileMock.LOGIN_JSON
                val jsonString: String

                jsonString = MockUtils().openStringFile(context, filename)
                return JsonUtils.gson.fromJson(jsonString, UserResponse::class.java)
            }
            else -> {
                // TODO Add heroku implementation service login
                return UserResponse(1, "", "")
            }
        }
    }
}