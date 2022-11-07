package com.app.appcentcase.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.app.appcentcase.databinding.FragmentNewsBinding
import com.app.appcentcase.utils.Resource
import com.app.appcentcase.view.activity.NewsDetailActivity
import com.app.appcentcase.view.adapter.ArticleAdapter
import com.app.appcentcase.viewmodel.MainViewModel


class NewsFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.articleData.observe(viewLifecycleOwner) { list ->
                when (list) {
                    is Resource.Success -> {
                        val adapter = ArticleAdapter()
                        adapter.itemClickListener = { item ->
                            startActivity(
                                Intent(
                                    requireContext(),
                                    NewsDetailActivity::class.java
                                ).also {
                                    it.putExtra("data", item)
                                })
                        }
                        binding.recyclerView.adapter = adapter
                        list.value?.let { adapter.list = it.articles }
                        binding.progressBar.visibility = View.GONE
                        binding.emptyListText1.visibility = View.GONE
                    }
                    is Resource.Failure -> Log.e("articleData", "articleData failure")
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.emptyListText1.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun initListeners() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.getArticle(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }


}