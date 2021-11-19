package com.alfian.moviecatalog3.ui.adapter

import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity

interface OnItemTvShowClickCallback {
    fun onItemClicked(tvShowEntity : TvShowEntity?)
}