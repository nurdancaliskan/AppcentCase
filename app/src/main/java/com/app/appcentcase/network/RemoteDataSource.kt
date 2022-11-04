package com.app.appcentcase.network

import com.app.appcentcase.data.model.ArticleModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSource {

    @GET("q=")
    fun searchArticle(@Query("searchWord") searchWord: String): Call<ArticleModel>
}

