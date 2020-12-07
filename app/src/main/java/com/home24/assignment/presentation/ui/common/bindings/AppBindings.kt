package com.home24.assignment.presentation.ui.common.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.home24.assignment.data.datasource.local.entities.ArticleEntity
import com.home24.assignment.presentation.ui.article.adapter.ArticleDetailListAdapter
import com.home24.assignment.presentation.ui.article.adapter.ArticleListAdapter

@BindingAdapter("articleList")
fun loadArticleList(
    recyclerView: RecyclerView,
    listOfArticle: List<ArticleEntity>?
) {
    listOfArticle?.let { list ->
        (recyclerView.adapter as ArticleListAdapter).updateList(list)
        recyclerView.scheduleLayoutAnimation()
    }
}

@BindingAdapter("articleDetailList")
fun loadArticleDetailList(
    recyclerView: RecyclerView,
    listOfArticle: List<ArticleEntity>?
) {
    listOfArticle?.let { list ->
        (recyclerView.adapter as ArticleDetailListAdapter).updateList(list)
        recyclerView.scheduleLayoutAnimation()
    }
}

@BindingAdapter("articleImage")
fun loadArticleImage(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context).load(imageUrl)
        .into(imageView)
}