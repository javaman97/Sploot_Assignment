package com.aman.sploot.repository

import com.aman.sploot.api.ApiService
import com.aman.sploot.model.Article
import com.aman.sploot.utils.ResourceState

class NewsRepositoryImpl(
    private val newsApi: ApiService,
): NewsRepository {

    override suspend fun getTopHeadlines(category: String): ResourceState<List<Article>> {
        return try {
            val response = newsApi.getBreakingNews(category = category)
            ResourceState.Success(data = response.articles)
        } catch (e: Exception) {
            ResourceState.Error(message = "Failed to fetch news ${e.message}")
        }
    }

    override suspend fun searchForNews(query: String): ResourceState<List<Article>> {
        return try {
            val response = newsApi.searchForNews(query = query)
            ResourceState.Success(data = response.articles)
        } catch (e: Exception) {
            ResourceState.Error(message = "Failed to fetch news ${e.message}")
        }
    }

}