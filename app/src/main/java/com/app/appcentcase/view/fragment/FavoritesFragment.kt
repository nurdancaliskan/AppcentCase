package com.app.appcentcase.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.app.appcentcase.databinding.FragmentFavoritesBinding
import com.app.appcentcase.utils.Resource
import com.app.appcentcase.view.adapter.ArticleAdapter
import com.app.appcentcase.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        viewModel.getArticlesFromLocal()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.articleList.observe(viewLifecycleOwner) { list ->
                when (list) {
                    is Resource.Success -> {
                        val adapter = ArticleAdapter()
                        binding.recyclerView.adapter = adapter
                        adapter.list = list.value
                        binding.progressBar.visibility = View.GONE
                        if (list.value.isNotEmpty()) {
                            binding.emptyListText2.visibility = View.GONE
                        }
                    }
                    is Resource.Failure -> Log.e("articleData", "articleData failure")
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}