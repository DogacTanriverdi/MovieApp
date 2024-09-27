package com.dogactanriverdi.movieapp.data.di

import android.content.Context
import androidx.room.Room
import com.dogactanriverdi.movieapp.common.Constants
import com.dogactanriverdi.movieapp.data.source.local.WatchListDao
import com.dogactanriverdi.movieapp.data.source.local.WatchListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideWatchListDatabase(
        @ApplicationContext context: Context
    ): WatchListDatabase {
        return Room.databaseBuilder(
            context,
            WatchListDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @[Provides Singleton]
    fun provideWatchListDao(watchListDatabase: WatchListDatabase): WatchListDao {
        return watchListDatabase.watchListDao()
    }
}