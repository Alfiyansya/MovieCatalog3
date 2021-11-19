package com.alfian.moviecatalog3.ui.adapter

import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity

interface OnItemMovieClickCallback {
    fun onItemClicked(movieEntity : MovieEntity?)
}