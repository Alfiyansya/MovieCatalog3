package com.alfian.moviecatalog3.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alfian.moviecatalog3.data.source.remote.api.ApiService
import com.alfian.moviecatalog3.data.source.remote.response.Movie
import com.alfian.moviecatalog3.data.source.remote.response.MovieDetailResponse
import com.alfian.moviecatalog3.data.source.remote.response.TvShow
import com.alfian.moviecatalog3.data.source.remote.response.TvShowDetailResponse
import com.alfian.moviecatalog3.util.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource(private val api: ApiService) {
    suspend fun getMovies(): LiveData<ApiResponse<List<Movie>>> {
        val resultMovie = MutableLiveData<ApiResponse<List<Movie>>>()
        try {
            EspressoIdlingResource.increment()
            val getData = api.getMovies().await().result
            resultMovie.postValue(ApiResponse.success(getData))
            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            e.printStackTrace()
            resultMovie.postValue(ApiResponse.error(e.message.toString()))
        }
        return resultMovie
    }

    suspend fun getDetailMovie(id: Int?): LiveData<ApiResponse<MovieDetailResponse>> {
        val resultDetail = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        try {
            EspressoIdlingResource.increment()
            val getData = api.getDetailMovie(id).await()
            resultDetail.postValue(ApiResponse.success(getData))
            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            e.printStackTrace()
            resultDetail.postValue(ApiResponse.error(e.message.toString()))
        }
        return resultDetail
    }

    suspend fun getTvShows(): LiveData<ApiResponse<List<TvShow>>> {
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShow>>>()
        try {
            EspressoIdlingResource.increment()
            val getData = api.getTvShows().await().result
            resultTvShow.postValue(ApiResponse.success(getData))
            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            e.printStackTrace()
            resultTvShow.postValue(ApiResponse.error(e.message.toString()))
        }
        return resultTvShow
    }

    suspend fun getDetailTvShow(id: Int?): LiveData<ApiResponse<TvShowDetailResponse>> {
        val resultDetail = MutableLiveData<ApiResponse<TvShowDetailResponse>>()
        try {
            EspressoIdlingResource.increment()
            val getData = api.getDetailTvShow(id).await()
            resultDetail.postValue(ApiResponse.success(getData))
            EspressoIdlingResource.decrement()
        } catch (e: Exception) {
            e.printStackTrace()
            resultDetail.postValue(ApiResponse.error(e.message.toString()))
        }
        return resultDetail
    }
}