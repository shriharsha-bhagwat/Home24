package com.home24.assignment.presentation.ui.detail

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.home24.assignment.R
import com.home24.assignment.databinding.ActivityArticleDetailBinding
import com.home24.assignment.presentation.core.base.BaseActivity
import com.home24.assignment.presentation.ui.article.ArticleListViewModel
import com.home24.assignment.presentation.ui.article.adapter.ArticleDetailListAdapter
import com.home24.assignment.presentation.utils.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * ArticleDetailActivity : This class represents the UI screen for loading of articles data
 */
class ArticleDetailActivity :
    BaseActivity<ArticleListViewModel, ActivityArticleDetailBinding>(R.layout.activity_article_detail) {

    override val mViewModel: ArticleListViewModel by viewModel()

    private var articleDetailListAdapter: ArticleDetailListAdapter? = null

    override fun observeViewModel() {
        mViewModel.gridLayout.observe(this, EventObserver {
            if (it) {
                binding.articleRv.layoutManager = GridLayoutManager(this, 2)
            } else {
                binding.articleRv.layoutManager = LinearLayoutManager(this)
            }
        })
    }

    override fun initView() {
        super.initView()
        setupBinding()
        setupAdapter()
    }

    /**
     * Sets up toolbar, search bar and FAB
     */
    private fun setupBinding() {
        setSupportActionBar(binding.toolbar)
    }


    private fun setupAdapter() {
        articleDetailListAdapter = ArticleDetailListAdapter(mViewModel)
        binding.articleRv.adapter = articleDetailListAdapter
    }

}