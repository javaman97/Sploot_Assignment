package com.aman.sploot.repository

import com.aman.sploot.model.Article
import com.aman.sploot.utils.ResourceState

interface NewsRepository {

    suspend fun getTopHeadlines(
        category: String
    ): ResourceState<List<Article>>


    suspend fun searchForNews(
        query: String
    ): ResourceState<List<Article>>
}
