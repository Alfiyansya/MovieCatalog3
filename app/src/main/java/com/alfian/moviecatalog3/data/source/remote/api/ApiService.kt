package com.alfian.moviecatalog3.data.source.remote.api

import com.alfian.moviecatalog3.BuildConfig
import com.alfian.moviecatalog3.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<ListResponse<Movie>>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<ListResponse<TvShow>>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") id: Int?,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<MovieDetailResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") id: Int?,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY

    ): Call<TvShowDetailResponse>

}