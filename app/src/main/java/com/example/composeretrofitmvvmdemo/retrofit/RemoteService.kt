package com.example.composeretrofitmvvmdemo.retrofit

import com.example.composeretrofitmvvmdemo.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor


object RemoteService {
    private const val baseURL = BuildConfig.BASE_URL

    //val interceptor

    private fun getRemoteInstance(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseURL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            it.header("Authorization", "Bearer 123")
                        }.build())
                    }
                    .also { client ->
                        if (BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.level = HttpLoggingInterceptor.Level.BODY
                            client.addInterceptor(logging)
                        }
                    }.build()
            )
            .build()

    fun getRemoteService(): RemoteAPI = getRemoteInstance().create(RemoteAPI::class.java)

}