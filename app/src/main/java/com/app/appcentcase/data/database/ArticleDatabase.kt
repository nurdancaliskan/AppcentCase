package com.app.appcentcase.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.appcentcase.data.dao.ArticleDao
import com.app.appcentcase.data.model.ArticleEntity


@Database(entities = [ArticleEntity::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        const val DATABASE_NAME = "ArticleDatabase"
    }
}
