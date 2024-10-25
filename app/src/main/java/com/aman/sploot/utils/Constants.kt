package com.aman.sploot.utils

object Constants {

    const val DEFAULT_COUNTRY = "us"  // USA
    const val API_KEY = "57d28635ecf441939482421742902756"
    const val BASE_URL = "https://newsapi.org/v2/"


    const val NEWS_DATABASE  = "news_database"
    const val ARTICLE_TABLE = "article_table"

    val CATEGORIES = listOf(
        "General", "Business", "Health", "Technology","Sports", "Entertainment", "Science",
    )
    var  PAGE_SIZE =  CATEGORIES.size
}