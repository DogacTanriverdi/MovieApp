package com.dogactanriverdi.movieapp.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dogactanriverdi.movieapp.common.Constants
import com.dogactanriverdi.movieapp.data.source.local.model.WatchListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWatchList(watchListEntity: WatchListEntity)

    @Delete
    suspend fun deleteFromWatchList(watchListEntity: WatchListEntity)

    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    fun getAllWatchList(): Flow<List<WatchListEntity>>
}