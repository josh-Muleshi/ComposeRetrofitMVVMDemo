package com.example.composeretrofitmvvmdemo.di

import com.example.composeretrofitmvvmdemo.MainRepo
import com.example.composeretrofitmvvmdemo.retrofit.RemoteAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    fun provideMainRepo(remoteAPI: RemoteAPI) = MainRepo(remoteAPI)

}