package com.app.appcentcase.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "ArticleDatabase")
data class ArticleEntity(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    @PrimaryKey(autoGenerate = false) val title: String,
    val url: String?,
    val urlToImage: String?
) : Parcelable