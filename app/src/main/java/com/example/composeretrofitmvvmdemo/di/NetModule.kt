package com.example.composeretrofitmvvmdemo.di

import com.example.composeretrofitmvvmdemo.BuildConfig
import com.example.composeretrofitmvvmdemo.MainRepo
import com.example.composeretrofitmvvmdemo.retrofit.RemoteAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseURL = BuildConfig.BASE_URL

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Provides
    fun provideRetrofitService(): RemoteAPI =
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
            .create(RemoteAPI::class.java)

}