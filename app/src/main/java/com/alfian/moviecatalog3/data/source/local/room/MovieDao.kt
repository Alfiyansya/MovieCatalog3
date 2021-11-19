package com.alfian.moviecatalog3.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovieList(query: SimpleSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Query("SElECT * FROM movieentity WHERE movieId = :movieId")
    fun getMovieDetail(movieId: String): LiveData<MovieEntity>

    @Query("SELECT * FROM movieentity WHERE isFavorite = :isFavorite")
    fun getFavoriteMovie(isFavorite: Boolean): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieEntity: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieEntity: MovieEntity)

    @Update
    suspend fun updateMovie(movieEntity: MovieEntity)


}