package com.aman.sploot.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity("articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @SerialName("author")
    val author: String? = "",
    @SerialName("content")
    val content: String? = "",
    @SerialName("description")
    val description: String? = "",
    @SerialName("publishedAt")
    var publishedAt: String = "",
    @SerialName("source")
    val source: Source? = Source(),
    @SerialName("title")
    val title: String?= "",
    @SerialName("url")
    val url: String? = "",
    @SerialName("urlToImage")
    val urlToImage: String? = ""
)