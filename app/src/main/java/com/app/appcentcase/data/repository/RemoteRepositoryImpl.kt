package com.app.appcentcase.data.repository

import com.app.appcentcase.data.model.ArticleModel
import com.app.appcentcase.di.modules.DataSourceModule
import com.app.appcentcase.network.RemoteDataSource
import com.app.appcentcase.utils.Resource
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    RemoteRepository {
    override suspend fun searchArticle(word: String): Resource<ArticleModel?> {
        val call = remoteDataSource.searchArticle(word, 1, DataSourceModule.apiKey)
        return if (call.isSuccessful) {
            val response = Resource.Success(call.body())
            response
        } else {
            val errorBody = Resource.Failure(true, call.code())
            errorBody
        }

    }
}
