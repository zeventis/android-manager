package br.com.zeventis.managerapp.core.api

import br.com.zeventis.managerapp.core.utils.Constants
import br.com.zeventis.managerapp.data.api.IApiService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideApi(retrofit: Retrofit): IApiService = retrofit.create(IApiService::class.java)

fun provideRetrofit(): Retrofit {
    lateinit var retrofit: Retrofit
    val client = OkHttpClient.Builder()
    addLoggingInterceptor(client)

    retrofit = Retrofit.Builder()
        .baseUrl(Constants.Network.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(client.build())
        .build()

    return retrofit
}

private fun addLoggingInterceptor(client: OkHttpClient.Builder) {
    client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
}
