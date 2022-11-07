package com.app.appcentcase.di.modules

import com.app.appcentcase.data.dao.ArticleDao
import com.app.appcentcase.data.repository.LocalRepository
import com.app.appcentcase.data.repository.LocalRepositoryImpl
import com.app.appcentcase.data.repository.RemoteRepository
import com.app.appcentcase.data.repository.RemoteRepositoryImpl
import com.app.appcentcase.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteRepository(
        apiDataStore: RemoteDataSource
    ): RemoteRepository = RemoteRepositoryImpl(apiDataStore)

    @Singleton
    @Provides
    fun provideLocalRepository(
        localRepository: ArticleDao
    ): LocalRepository = LocalRepositoryImpl(localRepository)

}