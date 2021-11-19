package com.alfian.moviecatalog3.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity

interface LocalDataSource {
    fun getMovieList(sort: String): DataSource.Factory<Int, MovieEntity>
    fun getTvShowList(sort: String): DataSource.Factory<Int, TvShowEntity>
    fun getFavoriteMovieList(): DataSource.Factory<Int, MovieEntity>
    fun getFavoriteTvShowList(): DataSource.Factory<Int, TvShowEntity>
    fun getMovieDetail(movieId: String): LiveData<MovieEntity>
    fun getTvShowDetail(tvShowId: String): LiveData<TvShowEntity>


    suspend fun updateMovie(movieEntity: MovieEntity)
    suspend fun updateTvShow(tvShowEntity: TvShowEntity)
    suspend fun insertMovieList(movieEntities: List<MovieEntity>)
    suspend fun insertTvShowList(tvShowEntities: List<TvShowEntity>)
    suspend fun insertMovieDetail(movieEntity: MovieEntity)
    suspend fun insertTvShowDetail(tvShowEntity: TvShowEntity)
}