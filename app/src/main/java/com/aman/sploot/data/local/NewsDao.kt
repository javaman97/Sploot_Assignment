package com.aman.sploot.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aman.sploot.model.Article


@Dao
interface NewsDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAllNews(articles: List<Article>)

        @Query("SELECT * FROM ARTICLE_TABLE")
        suspend fun getAllNewsArticles(): List<Article>

        @Query("SELECT * FROM ARTICLE_TABLE WHERE category = :category")
        suspend fun getArticlesByCategory(category: String): List<Article>


//        @Query("DELETE FROM ARTICLE_TABLE")
//        suspend fun deleteAll()
    }


