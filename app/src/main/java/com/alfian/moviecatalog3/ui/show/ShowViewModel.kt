package com.alfian.moviecatalog3.ui.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.data.source.repo.ShowRepository
import com.alfian.moviecatalog3.vo.Resource

class ShowViewModel constructor(private val showRepo : ShowRepository) : ViewModel() {
    fun getMovies(sort : String) : LiveData<Resource<PagedList<MovieEntity>>> = showRepo.getMovies(sort)
    fun getTvShows(sort : String) : LiveData<Resource<PagedList<TvShowEntity>>> = showRepo.getTvShows(sort)
}