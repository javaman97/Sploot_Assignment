package com.aman.sploot.api

import com.aman.sploot.model.News
import com.aman.sploot.utils.Constants
import com.aman.sploot.utils.Constants.API_KEY
import com.aman.sploot.utils.Constants.DEFAULT_COUNTRY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=57d28635ecf441939482421742902756

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("category") category: String,
        @Query("country") country: String = DEFAULT_COUNTRY,
        @Query("apiKey") apiKey: String = API_KEY
    ): News

    @GET("everything")
    suspend fun searchForNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): News

}

