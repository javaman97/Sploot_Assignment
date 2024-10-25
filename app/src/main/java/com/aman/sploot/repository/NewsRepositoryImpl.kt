package com.aman.sploot.repository

import com.aman.sploot.data.ApiService
import com.aman.sploot.data.local.NewsDao
import com.aman.sploot.model.Article
import com.aman.sploot.utils.NetworkUtil
import com.aman.sploot.utils.ResourceState

class NewsRepositoryImpl(
    private val newsApi: ApiService,
    private val newsDao: NewsDao,
    private val networkUtil: NetworkUtil
): NewsRepository {

    override suspend fun getTopHeadlines(category: String): ResourceState<List<Article>> {
        return if (networkUtil.isNetworkAvailable()) {
        try {
            val response = newsApi.getBreakingNews(category = category)
            val articles = response.articles?.map { article ->
                article.copy(category = category)
            }

            // Save articles to the database
            if (articles != null) {
                newsDao.insertAllNews(articles)
            }

            ResourceState.Success(data = response.articles)
        } catch (e: Exception) {
            ResourceState.Error(message = "Failed to fetch news ${e.message}")
        }
    } else {
        ResourceState.Error(message = "No internet connection")
    }
    }

    override suspend fun searchForNews(query: String): ResourceState<List<Article>> {
        return if (networkUtil.isNetworkAvailable()) {
            try {
                val response = newsApi.searchForNews(query = query)
                val articles = response.articles


                if (articles != null) {
                    newsDao.insertAllNews(articles)
                }
                ResourceState.Success(data = response.articles)
            } catch (e: Exception) {
                ResourceState.Error(message = "Failed to fetch news ${e.message}")
            }
        } else {
            ResourceState.Error(message = "No Internet connection")
        }
    }

    override suspend fun getLocalArticles(): List<Article> {
        return newsDao.getAllNewsArticles()
    }

    override suspend fun getLocalArticlesByCategory(category: String): List<Article> {
        return newsDao.getArticlesByCategory(category)
    }

}