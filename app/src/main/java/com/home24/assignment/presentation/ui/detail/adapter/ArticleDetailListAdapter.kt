package com.home24.assignment.presentation.ui.article.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home24.assignment.data.datasource.local.entities.ArticleEntity
import com.home24.assignment.databinding.ItemArticleBinding
import com.home24.assignment.databinding.ItemArticleDetailBinding
import com.home24.assignment.presentation.ui.article.ArticleListViewModel

/**
 * Adapter class for list of articles in the bounds.
 * The purpose of our Adapter class is to receive a List of data and bind it to our RecyclerView
 */

class ArticleDetailListAdapter(
    private val viewModel: ArticleListViewModel,
    private val isDetail: Boolean = false
) :
    RecyclerView.Adapter<ArticleDetailListAdapter.ArticleDetailListViewHolder>() {

    var mListRef: List<ArticleEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleDetailListViewHolder {
        return ArticleDetailListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArticleDetailListViewHolder, position: Int) {
        val article: ArticleEntity = mListRef.get(position)
        holder.bind(viewModel, article)
    }

    override fun getItemCount(): Int {
        return mListRef.size
    }

    class ArticleDetailListViewHolder private constructor(val binding: ItemArticleDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: ArticleListViewModel, bindItem: ArticleEntity) {
            binding.viewModel = viewModel
            binding.bindItem = bindItem
            binding.executePendingBindings()
        }

        companion object {

            fun from(parent: ViewGroup): ArticleDetailListViewHolder {

                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemArticleDetailBinding.inflate(layoutInflater, parent, false)

                return ArticleDetailListViewHolder(binding)
            }
        }
    }

    fun updateList(list: List<ArticleEntity>) {
        mListRef = list
        notifyDataSetChanged()
    }

}