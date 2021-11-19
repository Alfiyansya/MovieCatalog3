package com.alfian.moviecatalog3.data.source.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.vo.Resource

interface ShowDataSource {
    fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>
    fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>>
    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getDetailTvShow(id: Int): LiveData<Resource<TvShowEntity>>
}