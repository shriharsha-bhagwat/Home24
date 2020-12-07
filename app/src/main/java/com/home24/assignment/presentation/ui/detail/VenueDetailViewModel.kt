package com.home24.assignment.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.home24.assignment.domain.article.IArticleRepository
import com.home24.assignment.domain.article.VenueDetailRequest
import com.home24.assignment.domain.article.VenueDetailResponse.Response.Venue

import com.home24.assignment.presentation.core.base.BaseViewModel
import com.home24.assignment.presentation.utils.Event
import kotlinx.coroutines.launch

/**
 *  ViewModel for @link[ArticleDetailActivity]
 *  This ViewModel loads details of Venue from Repo when requested by UI.
 */

class VenueDetailViewModel(private val repository: IArticleRepository) : BaseViewModel() {

    private val _venue: MutableLiveData<Venue> = MutableLiveData()
    val venue: LiveData<Venue> = _venue

    private val _dataLoadEvent: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val dataLoadingEvent: LiveData<Event<Boolean>> = _dataLoadEvent


    /**
     * Helps loading list of venues from Repo when requested by UI.
     */
    fun getVenueDetail(venueID: String) {

        viewModelScope.launch {

            _dataLoadEvent.value = Event(true)

            val venueDetailRequest = VenueDetailRequest(
                venueID
            )



            _dataLoadEvent.value = Event(false)
        }
    }
}