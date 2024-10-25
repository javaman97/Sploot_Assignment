package com.aman.sploot.utils

import com.aman.sploot.model.Article

data class NewsScreenState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String? = null,
    val isSearchBarVisible: Boolean= false,
    val selectedArticle: Article? = null,
    val category: String = Constants.CATEGORIES[0],
    val searchQuery: String = ""
)
