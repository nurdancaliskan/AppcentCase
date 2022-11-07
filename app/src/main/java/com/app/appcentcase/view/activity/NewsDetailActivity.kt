package com.app.appcentcase.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.appcentcase.data.model.ArticleModel
import com.app.appcentcase.databinding.ActivityNewsDetailBinding
import com.app.appcentcase.utils.Resource
import com.app.appcentcase.viewmodel.NewsDetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {

    val viewModel: NewsDetailViewModel by viewModels()

    private lateinit var binding: ActivityNewsDetailBinding
    private lateinit var articleData: ArticleModel.Article

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getArticlesFromLocal()
        articleData = intent.extras?.get("data") as ArticleModel.Article
        initListeners()
        initViews()
        initObservers()

    }

    private fun initViews() {
        binding.apply {
            Glide.with(this@NewsDetailActivity).load(articleData.urlToImage).into(postDetailImg)
            postDetailTitle.text = articleData.title
            postDetailAuthor.text = articleData.author
            postDetailDate.text = articleData.publishedAt
            postDetailDescription.text = articleData.description
        }
        setFavourite()
    }

    private fun setFavourite() {
        if (isFavorite) {
            Glide.with(this).load(
                this.resources.getIdentifier(
                    "ic_baseline_favorite_24", "drawable", this.packageName
                )
            ).into(binding.favoritesBtn)
        } else {
            Glide.with(this).load(
                this.resources.getIdentifier(
                    "ic_baseline_favorite_border_24", "drawable", this.packageName
                )
            ).into(binding.favoritesBtn)
        }
    }

    private fun initListeners() {
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, articleData.url)
            val chooser = Intent.createChooser(intent, "Share using..")
            startActivity(chooser)
        }

        binding.postDetailNewsSourceBtn.setOnClickListener {
            val intent = Intent(this@NewsDetailActivity, NewsSourceActivity::class.java)
            intent.putExtra("source", articleData.url)
            startActivity(intent)
        }

        binding.favoritesBtn.setOnClickListener {
            if (!isFavorite) {
                viewModel.insertArticle(articleData)
                isFavorite = true
                setFavourite()
            } else {
                viewModel.deleteArticle(articleData)
                isFavorite = false
                setFavourite()
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.articleList.observe(this@NewsDetailActivity) { list ->
                when (list) {
                    is Resource.Success -> {
                        list.value.forEach {
                            isFavorite = it.title == articleData.title
                        }
                        setFavourite()
                    }
                    is Resource.Failure -> Log.e("articleData", "articleData failure")
                    is Resource.Loading -> Unit
                }
            }
        }
    }

}