package com.app.appcentcase.utils

sealed class Resource<out T> {

    data class Success<out T>(val value: T) : Resource<T>()

    data class Failure(
        val errorBody: Boolean,
        val errorCode: Int?,
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}
