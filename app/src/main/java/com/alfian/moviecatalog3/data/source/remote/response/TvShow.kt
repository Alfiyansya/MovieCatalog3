package com.alfian.moviecatalog3.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShow(
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
)