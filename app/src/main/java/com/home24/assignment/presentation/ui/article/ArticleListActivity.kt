package com.home24.assignment.presentation.ui.article

import android.content.Intent
import com.home24.assignment.R
import com.home24.assignment.core.utils.AppConstants
import com.home24.assignment.databinding.ActivityArticleListBinding
import com.home24.assignment.presentation.core.base.BaseActivity
import com.home24.assignment.presentation.ui.article.adapter.ArticleListAdapter
import com.home24.assignment.presentation.ui.detail.ArticleDetailActivity
import com.home24.assignment.presentation.utils.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * ArticleListActivity : This class represents the UI screen for loading of article data from the Server
 * Contains : A Recyclerview for loading Data items
 */
class ArticleListActivity :
    BaseActivity<ArticleListViewModel, ActivityArticleListBinding>(R.layout.activity_article_list) {

    override val mViewModel: ArticleListViewModel by viewModel()

    private var articleListAdapter: ArticleListAdapter? = null

    override fun observeViewModel() {

        mViewModel.dataLoadingEvent.observe(this, EventObserver { showLoading ->
            if (showLoading) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        })

        mViewModel.articles.observe(this, { articles ->
            mViewModel.currentDecisionCount.value?.let {
                binding.articleRv.scrollToPosition(it.peekContent())
            }
        })

        mViewModel.currentDecisionCount.observe(this, EventObserver { countOfDecisionTaken ->
            if (countOfDecisionTaken == AppConstants.APP_COMMON_ARTICLE_LIMIT) {
                binding.reviewBtn.alpha = 1F
                binding.reviewBtn.isEnabled = true
            } else {
                binding.reviewBtn.alpha = 0.5F
                binding.reviewBtn.isEnabled = false
            }
        })

        mViewModel.onReviewEvent.observe(this, EventObserver {
            val intent = Intent(this, ArticleDetailActivity::class.java)
            startActivity(intent)
        })
    }

    override fun initView() {
        super.initView()
        setupBinding()
        setupAdapter()
        loadData()
    }

    private fun loadData() {
        mViewModel.getArticleData()
    }


    /**
     * Sets up toolbar, search bar and FAB
     */
    private fun setupBinding() {
        setSupportActionBar(binding.toolbar)
    }


    private fun setupAdapter() {
        articleListAdapter = ArticleListAdapter(mViewModel)
        binding.articleRv.adapter = articleListAdapter
    }

}