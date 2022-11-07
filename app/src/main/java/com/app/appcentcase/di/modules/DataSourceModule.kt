package com.app.appcentcase.di.modules

import com.app.appcentcase.network.RemoteDataSource
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    const val apiKey = "a68d13ca723f47c0bdbc8d464fbe4bb0"

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))

        return retrofit.build().create(RemoteDataSource::class.java)
    }
}
