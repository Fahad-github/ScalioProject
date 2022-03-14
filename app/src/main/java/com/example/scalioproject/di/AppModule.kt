package com.example.scalioproject.di

import com.example.scalioproject.data.mapper.UserMapper
import com.example.scalioproject.data.remote.WebService
import com.example.scalioproject.data.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://api.github.com/"


    @Provides
    @Singleton
    fun provideWebService(baseUrl: String): WebService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(WebService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(webService: WebService) = UsersRepository(webService = webService)

    @Provides
    @Singleton
    fun provideUserMapper() = UserMapper()
}