package br.com.zeventis.managerapp.core.network

import br.com.zeventis.managerapp.BuildConfig
import br.com.zeventis.managerapp.data.api.IApiCore
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideApi(retrofit: Retrofit): IApiCore = retrofit.create(IApiCore::class.java)


fun provideRetrofit(authInterceptor: AuthInterceptor): Retrofit {
    lateinit var retrofit: Retrofit
    val httpClient = OkHttpClient.Builder()

    addLoggingInterceptor(httpClient)
    addAuthInterceptor(httpClient, authInterceptor)

    retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(httpClient.build())
        .build()

    return retrofit
}

private fun addLoggingInterceptor(client: OkHttpClient.Builder) {
    client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
}

private fun addAuthInterceptor(client: OkHttpClient.Builder, authInterceptor: AuthInterceptor) {
    client.addInterceptor(authInterceptor)
}
