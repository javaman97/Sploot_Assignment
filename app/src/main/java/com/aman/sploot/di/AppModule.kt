package com.aman.sploot.di

import android.content.Context
import androidx.room.Room
import com.aman.sploot.data.ApiService
import com.aman.sploot.data.local.NewsDB
import com.aman.sploot.data.local.NewsDao
import com.aman.sploot.data.local.NewsTypeConvertor
import com.aman.sploot.repository.NewsRepository
import com.aman.sploot.repository.NewsRepositoryImpl
import com.aman.sploot.utils.Constants.BASE_URL
import com.aman.sploot.utils.Constants.NEWS_DATABASE
import com.aman.sploot.utils.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: ApiService, newsDao: NewsDao, networkUtil: NetworkUtil): NewsRepository {
        return NewsRepositoryImpl(newsApi = newsApi, newsDao = newsDao, networkUtil = networkUtil)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkUtil {
        return NetworkUtil(context)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDB {
        return Room.databaseBuilder(
            context,
            NewsDB::class.java,
            NEWS_DATABASE
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideArticleDao(database: NewsDB): NewsDao {
        return database.newsDao()
    }

}