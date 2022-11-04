package com.app.appcentcase.data.repository

import com.app.appcentcase.data.model.ArticleModel
import com.app.appcentcase.network.RemoteDataSource
import retrofit2.Call
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    RemoteRepository {
    override suspend fun searchArticle(word: String): Call<ArticleModel> {
        return remoteDataSource.searchArticle(word)
    }
}
