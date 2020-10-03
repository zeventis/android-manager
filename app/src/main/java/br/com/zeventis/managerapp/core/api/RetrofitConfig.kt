package br.com.zeventis.managerapp.core.api

import br.com.zeventis.managerapp.BuildConfig
import br.com.zeventis.managerapp.R
import br.com.zeventis.managerapp.core.ZeventisApplication
import br.com.zeventis.managerapp.data.api.IApiCore
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideApi(retrofit: Retrofit): IApiCore = retrofit.create(IApiCore::class.java)

fun provideRetrofit(): Retrofit {
    lateinit var retrofit: Retrofit
    val httpClient = OkHttpClient.Builder()
    httpClient.authenticator { _, response ->
        response.request
            .newBuilder()
            .header("Authorization", "Bearer " + ZeventisApplication().getUserToken())
            .build()
    }

    addLoggingInterceptor(httpClient)

    retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.FLAVOR)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(httpClient.build())
        .build()

    return retrofit
}

private fun addLoggingInterceptor(client: OkHttpClient.Builder) {
    client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
}
