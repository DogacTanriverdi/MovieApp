package com.dogactanriverdi.movieapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dogactanriverdi.movieapp.data.source.local.model.WatchListEntity

@Database(entities = [WatchListEntity::class], version = 1)
abstract class WatchListDatabase : RoomDatabase() {
    abstract fun watchListDao(): WatchListDao
}