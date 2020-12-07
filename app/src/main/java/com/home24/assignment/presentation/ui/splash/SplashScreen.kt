package com.home24.assignment.presentation.ui.splash

import android.content.Intent
import com.home24.assignment.R
import com.home24.assignment.databinding.ActivitySplashBinding
import com.home24.assignment.presentation.core.base.BaseActivity
import com.home24.assignment.presentation.ui.article.ArticleListActivity
import com.home24.assignment.presentation.utils.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreen :
    BaseActivity<SplashViewModel, ActivitySplashBinding>(R.layout.activity_splash) {

    override val mViewModel: SplashViewModel by viewModel()

    override fun initView() {
        super.initView()
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this@SplashScreen, ArticleListActivity::class.java)
        startActivity(intent)
        overridePendingTransition(
            R.anim.modal_activity_open_enter,
            R.anim.modal_activity_close_exit
        )
    }

    override fun observeViewModel() {
        mViewModel.onStartEvent.observe(this, EventObserver {
            navigateToMainScreen()
        })
    }
}