package com.home24.assignment.presentation.ui.article

import androidx.lifecycle.*
import com.home24.assignment.data.datasource.local.entities.ArticleEntity
import com.home24.assignment.domain.core.ResultState
import com.home24.assignment.domain.core.SimpleResult
import com.home24.assignment.domain.article.IArticleRepository
import com.home24.assignment.presentation.core.base.BaseViewModel
import com.home24.assignment.presentation.utils.Event
import kotlinx.coroutines.launch

/**
 *  Viewmodel for ArticleListLActivity. Helps loading list of Articles from Repo when requested by UI.
 */

class ArticleListViewModel(private val repository: IArticleRepository) : BaseViewModel() {

    private val _currentDecisionCount: MutableLiveData<Event<Int>> = MutableLiveData()
    val currentDecisionCount: LiveData<Event<Int>> = _currentDecisionCount

    private val _likeCount: MutableLiveData<Event<Int>> = MutableLiveData()
    val likeCount: LiveData<Event<Int>> = _likeCount

    private val _dataLoadEvent: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val dataLoadingEvent: LiveData<Event<Boolean>> = _dataLoadEvent

    private val _onReviewEvent: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val onReviewEvent: LiveData<Event<Boolean>> = _onReviewEvent

    private val _gridLayout: MutableLiveData<Event<Boolean>> = MutableLiveData(Event(false))
    val gridLayout: LiveData<Event<Boolean>> = _gridLayout

    private val _articles: LiveData<SimpleResult<List<ArticleEntity>>> =
        liveData {
            emitSource(repository.observeArticles())
        }

    /**
     * Live data observing channels from the database.
     */
    val articles: LiveData<List<ArticleEntity>> = _articles.map {
        when (it) {
            is ResultState.Success -> {

                val countOfDecisionTaken = it.data.count { it.isDisLiked || it.isLiked }
                _currentDecisionCount.value = Event(countOfDecisionTaken)

                val likeCount = it.data.count { it.isLiked }
                _likeCount.value = Event(likeCount)

                it.data
            }
            is ResultState.Failure -> {
                emptyList()
            }
        }
    }

    /**
     * Helps loading list of articles from Repo when requested by UI.
     */
    fun getArticleData() {

        viewModelScope.launch {

            _dataLoadEvent.value = Event(true)

            repository.getListOfArticles().fold(
                success = {
                    _onMessageEvent.value = Event("Loaded Venues to DB")
                },
                failure = {
                    _onErrorEvent.value = Event(it.localizedMessage)
                }
            )

            _dataLoadEvent.value = Event(false)
        }
    }


    fun onLikeArticle(articleID: String, decision: Boolean) {

        viewModelScope.launch {

            val article = articles.value?.filter { it.id == articleID }?.firstOrNull()
            article?.let {

                if (decision) {
                    it.isLiked = decision
                    it.isDisLiked = !decision
                } else {
                    it.isDisLiked = !decision
                    it.isLiked = decision
                }

                repository.updateArticle(it)
            }
        }
    }

    fun onReviewEvent() {
        _onReviewEvent.value = Event(true)
    }

    fun onGridLayoutSelected() {
        gridLayout.value?.peekContent()?.let { isGrid ->
            _gridLayout.value = Event(!isGrid)
        }
    }
}