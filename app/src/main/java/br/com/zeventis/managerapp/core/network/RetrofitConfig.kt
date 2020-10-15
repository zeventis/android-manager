package br.com.zeventis.managerapp.core.network

import br.com.zeventis.managerapp.BuildConfig
import br.com.zeventis.managerapp.data.api.IApiCore
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideApi(retrofit: Retrofit): IApiCore = retrofit.create(IApiCore::class.java)

// TODO Add network verify interceptor
fun provideRetrofit(authInterceptor: AuthInterceptor): Retrofit {
    lateinit var retrofit: Retrofit
    val httpClient = OkHttpClient.Builder()

    addInterceptors(httpClient, authInterceptor)

    retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(httpClient.build())
        .build()

    return retrofit
}

private fun addInterceptors(httpClient: OkHttpClient.Builder, authInterceptor: AuthInterceptor) {
    httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    httpClient.addInterceptor(authInterceptor)
}
