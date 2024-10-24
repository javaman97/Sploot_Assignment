package com.aman.sploot.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    @SerialName("articles")
    val articles: List<Article>? = listOf(),
    @SerialName("status")
    val status: String? = "",
    @SerialName("totalResults")
    val totalResults: Int ?= 0
)