package com.app.appcentcase.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.appcentcase.data.model.ArticleModel
import com.app.appcentcase.databinding.ListItemNewsBinding
import com.bumptech.glide.Glide

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.VH>() {

    var itemClickListener: ((item: ArticleModel.Article) -> Unit)? = null

    var list = listOf<ArticleModel.Article>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        return VH(ListItemNewsBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class VH(
        private val binding: ListItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticleModel.Article) {
            Glide.with(binding.root.context).load(data.urlToImage).into(binding.listItemImage)
            binding.listItemTitle.text = data.title
            binding.listItemDescription.text = data.description
            binding.root.setOnClickListener {
                itemClickListener?.invoke(data)
            }
        }
    }

    override fun onBindViewHolder(
        holder: VH, position: Int
    ) {
        holder.bind(list[position])
    }
}
