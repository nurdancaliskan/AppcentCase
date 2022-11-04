package com.app.appcentcase.data.repository

import com.app.appcentcase.data.model.ArticleModel
import retrofit2.Call

interface RemoteRepository {

    suspend fun searchArticle(word: String): Call<ArticleModel>
}