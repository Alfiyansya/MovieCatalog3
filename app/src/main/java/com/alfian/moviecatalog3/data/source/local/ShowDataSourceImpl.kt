package com.alfian.moviecatalog3.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.data.source.local.room.MovieDao
import com.alfian.moviecatalog3.data.source.local.room.TvShowDao
import com.alfian.moviecatalog3.util.SortUtils
import com.alfian.moviecatalog3.util.SortUtils.MOVIE_ENTITY
import com.alfian.moviecatalog3.util.SortUtils.TV_SHOW_ENTITY

class ShowDataSourceImpl(
    private val movieDao: MovieDao, private val tvShowDao: TvShowDao
) : LocalDataSource {
    override fun getMovieList(sort: String):
            DataSource.Factory<Int, MovieEntity> =
        movieDao.getMovieList(SortUtils.getSortedQuery(sort, MOVIE_ENTITY))

    override fun getTvShowList(sort: String):
            DataSource.Factory<Int, TvShowEntity> =
        tvShowDao.getTvShowList(SortUtils.getSortedQuery(sort, TV_SHOW_ENTITY))

    override fun getFavoriteMovieList(): DataSource.Factory<Int, MovieEntity> =
        movieDao.getFavoriteMovie(true)

    override fun getFavoriteTvShowList(): DataSource.Factory<Int, TvShowEntity> =
        tvShowDao.getFavoriteTvShow(true)

    override fun getMovieDetail(movieId: String):
            LiveData<MovieEntity> = movieDao.getMovieDetail(movieId)

    override fun getTvShowDetail(tvShowId: String):
            LiveData<TvShowEntity> = tvShowDao.getTvShowDetail(tvShowId)

    override suspend fun updateMovie(movieEntity: MovieEntity) =
        movieDao.updateMovie(movieEntity)

    override suspend fun updateTvShow(tvShowEntity: TvShowEntity) =
        tvShowDao.updateTvShow(tvShowEntity)

    override suspend fun insertMovieList(movieEntities: List<MovieEntity>) =
        movieDao.insertMovieList(movieEntities)

    override suspend fun insertTvShowList(tvShowEntities: List<TvShowEntity>) =
        tvShowDao.insertTvShowList(tvShowEntities)

    override suspend fun insertMovieDetail(movieEntity: MovieEntity) =
        movieDao.insertMovieDetail(movieEntity)

    override suspend fun insertTvShowDetail(tvShowEntity: TvShowEntity) =
        tvShowDao.insertTvShowDetail(tvShowEntity)
}