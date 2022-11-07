package com.app.appcentcase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.appcentcase.data.model.ArticleModel
import com.app.appcentcase.data.repository.LocalRepository
import com.app.appcentcase.data.repository.RemoteRepository
import com.app.appcentcase.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    val articleData: MutableLiveData<Resource<ArticleModel?>>
        get() = _articleData

    private val _articleData = MutableLiveData<Resource<ArticleModel?>>()

    val articleList: MutableLiveData<Resource<List<ArticleModel.Article>>>
        get() = _articleList
    private val _articleList = MutableLiveData<Resource<List<ArticleModel.Article>>>()


    fun getArticle(word: String) = viewModelScope.launch {
        _articleData.value = Resource.Loading
        _articleData.value = remoteRepository.searchArticle(word)
    }

    fun getArticlesFromLocal() = viewModelScope.launch {
        _articleList.value = Resource.Loading
        _articleList.value = Resource.Success(localRepository.getArticles())
    }

}