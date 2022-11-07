package com.app.appcentcase.network

import com.app.appcentcase.data.model.ArticleModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSource {

    @GET("everything")
    suspend fun searchArticle(
        @Query("q") searchWord: String, @Query("page") page: Int, @Query("apiKey") apiKey: String
    ): Response<ArticleModel>
}

