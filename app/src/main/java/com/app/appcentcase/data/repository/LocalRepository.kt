package com.app.appcentcase.data.repository

import com.app.appcentcase.data.model.ArticleModel

interface LocalRepository {

    suspend fun insertArticle(article: ArticleModel.Article)

    suspend fun deleteArticle(article: ArticleModel.Article)

    suspend fun getArticles(): List<ArticleModel.Article>
}