package com.alfian.moviecatalog3.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alfian.moviecatalog3.data.source.local.entity.*

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 11,
    exportSchema = false
)
abstract class ShowDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}