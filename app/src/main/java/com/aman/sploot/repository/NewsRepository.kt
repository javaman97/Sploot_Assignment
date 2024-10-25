package com.aman.sploot.repository

import com.aman.sploot.data.model.Article
import com.aman.sploot.utils.ResourceState

interface NewsRepository {

    suspend fun getTopHeadlines(
        category: String
    ): ResourceState<List<Article>>


    suspend fun searchForNews(
        query: String
    ): ResourceState<List<Article>>

     suspend fun getLocalArticles(): List<Article>

    suspend fun  getLocalArticlesByCategory(category: String):List<Article>

}
