package com.aman.sploot.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aman.sploot.model.Article
import com.aman.sploot.model.News

@Database(entities = [Article::class], version = 2)
 @TypeConverters(NewsTypeConvertor::class)
abstract class NewsDB : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}