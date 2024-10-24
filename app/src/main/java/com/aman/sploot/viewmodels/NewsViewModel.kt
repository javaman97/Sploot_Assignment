package com.aman.sploot.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aman.sploot.api.ApiService
import com.aman.sploot.model.News
import com.aman.sploot.repository.NewsRepository
import com.aman.sploot.utils.Constants
import com.aman.sploot.utils.ResourceState
import com.aman.sploot.views.NewsScreenEvent
import com.aman.sploot.views.NewsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    var state by mutableStateOf(NewsScreenState())

    private var searchJob: Job? = null

    fun onEvent(event: NewsScreenEvent) {
        when (event) {
            is NewsScreenEvent.OnCategoryChanged -> {
                state = state.copy(category = event.category)
                getNewsArticles(category = state.category)
            }

            is NewsScreenEvent.OnNewsCardClicked -> {
                state = state.copy(selectedArticle = event.article)
            }

            NewsScreenEvent.OnSearchIconClicked -> {
                state = state.copy(
                    isSearchBarVisible = true,
                    articles = emptyList()
                )
            }

            NewsScreenEvent.OnCloseIconClicked -> {
                state = state.copy(isSearchBarVisible = false)
                getNewsArticles(category = state.category)
            }

            is NewsScreenEvent.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = event.searchQuery)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000L)
                    searchForNews(query = state.searchQuery)
                }
            }
        }
    }

    private fun getNewsArticles(category: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = newsRepository.getTopHeadlines(category = category)
            when (result) {
                is ResourceState.Success -> {
                    state = state.copy(
                        articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is ResourceState.Error -> {
                    state = state.copy(
                        articles = emptyList(),
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    private fun searchForNews(query: String) {
        if (query.isEmpty()) {
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = newsRepository.searchForNews(query = query)
            when (result) {
                is ResourceState.Success -> {
                    state = state.copy(
                        articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is ResourceState.Error -> {
                    state = state.copy(
                        articles = emptyList(),
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

}
