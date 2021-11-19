package com.alfian.moviecatalog3.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity

@Dao
interface TvShowDao {
    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getTvShowList(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvShowEntity>

    @Query("SElECT * FROM tvshowentity WHERE tvShowId = :tvShowId")
    fun getTvShowDetail(tvShowId: String): LiveData<TvShowEntity>

    @Query("SELECT * FROM tvshowentity WHERE isFavorite = :isFavorite")
    fun getFavoriteTvShow(isFavorite: Boolean): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowList(TvShowEntity: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowDetail(TvShowEntity: TvShowEntity)

    @Update
    suspend fun updateTvShow(TvShowEntity: TvShowEntity)


}