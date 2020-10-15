package br.com.zeventis.managerapp.core.network

import android.content.Context
import br.com.zeventis.managerapp.core.utils.Constants
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val user = sessionManager.getUser()

        if (user != null && chain.request().url.encodedPath != Constants.Router.AUTHENTICATION && chain.request().url.encodedPath != Constants.Router.REGISTER) {
            requestBuilder.addHeader("Authorization", "Bearer ${user.token}")
        } else {
            Response.Builder()
                .code(403)
                .protocol(Protocol.HTTP_2)
                .message("Usuário não logado")
                .request(chain.request())
                .build()
        }

        return chain.proceed(requestBuilder.build())
    }
}