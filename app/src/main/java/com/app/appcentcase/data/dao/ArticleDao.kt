package com.app.appcentcase.data.dao

import androidx.room.*
import com.app.appcentcase.data.model.ArticleEntity

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleEntity)

    @Delete
    suspend fun deleteArticle(article: ArticleEntity)

    @Query("SELECT * FROM ArticleDatabase")
    suspend fun getArticles(): List<ArticleEntity>
}