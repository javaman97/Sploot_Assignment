package com.aman.sploot.di

import android.content.Context
import androidx.room.Room
import com.aman.sploot.api.ApiService
import com.aman.sploot.repository.NewsRepository
import com.aman.sploot.repository.NewsRepositoryImpl
import com.aman.sploot.utils.Constants.BASE_URL
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
    fun provideNewsRepository(newsApi: ApiService): NewsRepository {
        return NewsRepositoryImpl(newsApi = newsApi)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkUtil {
        return NetworkUtil(context)
    }

//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
//        return Room.databaseBuilder(
//            context,
//            NewsDB::class.java,
//            "news_database"
//        ).build()
//    }

}