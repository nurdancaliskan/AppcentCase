package com.app.appcentcase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.appcentcase.data.model.ArticleModel
import com.app.appcentcase.data.repository.LocalRepository
import com.app.appcentcase.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(private val localRepository: LocalRepository) :
    ViewModel() {

    val articleList: MutableLiveData<Resource<List<ArticleModel.Article>>>
        get() = _articleList
    private val _articleList = MutableLiveData<Resource<List<ArticleModel.Article>>>()


    fun insertArticle(article: ArticleModel.Article) = viewModelScope.launch {
        localRepository.insertArticle(article)
    }

    fun getArticlesFromLocal() = viewModelScope.launch {
        _articleList.value = Resource.Loading
        _articleList.value = Resource.Success(localRepository.getArticles())

    }

    fun deleteArticle(article: ArticleModel.Article) = viewModelScope.launch {
        localRepository.deleteArticle(article)
    }
}