package com.alfian.moviecatalog3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.data.source.repo.ShowRepository
import com.alfian.moviecatalog3.vo.Resource

class DetailViewModel constructor(private val repo: ShowRepository) : ViewModel() {

    fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> = repo.getDetailMovie(id)
    fun getDetailTvShow(id: Int): LiveData<Resource<TvShowEntity>> = repo.getDetailTvShow(id)
    fun setFavoriteMovie(movieEntity: MovieEntity, isFavorite: Boolean) {
        repo.setFavoriteMovie(movieEntity, isFavorite)
    }

    fun setFavoriteTvShow(tvShowEntity: TvShowEntity, isFavorite: Boolean) {
        repo.setFavoriteTvShow(tvShowEntity, isFavorite)
    }
}