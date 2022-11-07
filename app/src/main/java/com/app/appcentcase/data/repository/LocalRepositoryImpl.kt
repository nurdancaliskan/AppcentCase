package com.app.appcentcase.data.repository

import com.app.appcentcase.data.dao.ArticleDao
import com.app.appcentcase.data.model.ArticleEntity
import com.app.appcentcase.data.model.ArticleModel
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val localDataSource: ArticleDao) :
    LocalRepository {
    override suspend fun insertArticle(article: ArticleModel.Article) {
        val articleEntity = article.title?.let {
            ArticleEntity(
                author = article.author,
                content = article.content,
                description = article.description,
                publishedAt = article.publishedAt,
                title = it,
                url = article.url,
                urlToImage = article.urlToImage,
            )
        }
        articleEntity?.let { localDataSource.insertArticle(it) }
    }

    override suspend fun deleteArticle(article: ArticleModel.Article) {
        val articleEntity = article.title?.let {
            ArticleEntity(
                author = article.author,
                content = article.content,
                description = article.description,
                publishedAt = article.publishedAt,
                title = it,
                url = article.url,
                urlToImage = article.urlToImage,
            )
        }
        articleEntity?.let { localDataSource.deleteArticle(it) }
    }

    override suspend fun getArticles(): List<ArticleModel.Article> {
        val list = localDataSource.getArticles()
        val returnList = ArrayList<ArticleModel.Article>()
        list.forEach {
            val article = ArticleModel.Article(
                author = it.author,
                content = it.content,
                description = it.description,
                publishedAt = it.publishedAt,
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage,
                source = null
            )
            returnList.add(article)
        }
        return returnList
    }
}