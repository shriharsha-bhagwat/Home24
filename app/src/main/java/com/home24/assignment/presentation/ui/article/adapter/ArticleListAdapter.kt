package com.home24.assignment.presentation.ui.article.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home24.assignment.data.datasource.local.entities.ArticleEntity
import com.home24.assignment.databinding.ItemArticleBinding
import com.home24.assignment.presentation.ui.article.ArticleListViewModel

/**
 * Adapter class for list of articles in the bounds.
 * The purpose of our Adapter class is to receive a List of data and bind it to our RecyclerView
 */

class ArticleListAdapter(private val viewModel: ArticleListViewModel) :
    RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder>() {

    var mListRef: List<ArticleEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        return ArticleListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        val article: ArticleEntity = mListRef.get(position)
        holder.bind(viewModel, article)
    }

    override fun getItemCount(): Int {
        return mListRef.size
    }

    class ArticleListViewHolder private constructor(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: ArticleListViewModel, bindItem: ArticleEntity) {
            binding.viewModel = viewModel
            binding.bindItem = bindItem
            binding.executePendingBindings()
        }

        companion object {

            fun from(parent: ViewGroup): ArticleListViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemArticleBinding.inflate(layoutInflater, parent, false)

                return ArticleListViewHolder(binding)
            }
        }
    }

    fun updateList(list: List<ArticleEntity>) {
        mListRef = list
        notifyDataSetChanged()
    }

}