package com.app.appcentcase.data.repository

import com.app.appcentcase.data.model.ArticleModel
import com.app.appcentcase.utils.Resource

interface RemoteRepository {

    suspend fun searchArticle(word: String): Resource<ArticleModel?>
}