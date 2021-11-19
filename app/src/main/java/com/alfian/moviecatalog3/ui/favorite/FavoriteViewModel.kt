package com.alfian.moviecatalog3.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.data.source.repo.ShowRepository

class FavoriteViewModel constructor(private val repo : ShowRepository) : ViewModel() {
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = repo.getFavoriteMovie()
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = repo.getFavoriteTvShow()
}