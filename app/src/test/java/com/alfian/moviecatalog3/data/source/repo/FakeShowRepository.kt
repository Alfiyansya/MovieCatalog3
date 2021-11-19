package com.alfian.moviecatalog3.data.source.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alfian.moviecatalog3.data.source.local.LocalDataSource
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.data.source.remote.ApiResponse
import com.alfian.moviecatalog3.data.source.remote.NetworkBoundResource
import com.alfian.moviecatalog3.data.source.remote.RemoteDataSource
import com.alfian.moviecatalog3.data.source.remote.ShowDataSource
import com.alfian.moviecatalog3.data.source.remote.response.Movie
import com.alfian.moviecatalog3.data.source.remote.response.MovieDetailResponse
import com.alfian.moviecatalog3.data.source.remote.response.TvShow
import com.alfian.moviecatalog3.data.source.remote.response.TvShowDetailResponse
import com.alfian.moviecatalog3.vo.Resource
import kotlinx.coroutines.CoroutineScope
import java.util.*

class FakeShowRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val coroutineScope: CoroutineScope
) :
    ShowDataSource {
    override fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(coroutineScope) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovieList(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<Movie>?) {
                val movieList = ArrayList<MovieEntity>()
                if (data != null) {
                    for (response in data) {
                        val movie = MovieEntity(
                            id = response.id.toString(),
                            title = response.title,
                            genres = "",
                            overview = response.overview,
                            imagePath = response.posterPath.toString(),
                            rating = response.voteAverage
                        )
                        movieList.add(movie)
                    }
                }
                localDataSource.insertMovieList(movieList)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(coroutineScope) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMovieDetail(id.toString())
            }

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null  && data.genres == ""


            override suspend fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> {
                return remoteDataSource.getDetailMovie(id)
            }

            override suspend fun saveCallResult(data: MovieDetailResponse?) {
                val genres = StringBuilder().append("")

                for (i in data?.genres?.indices!!) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                val detailMovie = MovieEntity(
                    id = data.id.toString(),
                    title =data.title,
                    genres = genres.toString(),
                    overview = data.overview,
                    imagePath = data.posterPath,
                    rating = data.voteAverage,
                )
                localDataSource.updateMovie(detailMovie)
            }

        }.asLiveData()
    }

    override fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShow>>(coroutineScope) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShowList(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): LiveData<ApiResponse<List<TvShow>>> {
                return remoteDataSource.getTvShows()
            }

            override suspend fun saveCallResult(data: List<TvShow>?) {
                val tvShowList = ArrayList<TvShowEntity>()
                if (data != null) {
                    for (response in data) {
                        val tvShow = TvShowEntity(
                            id = response.id.toString(),
                            name = response.name,
                            genres = "",
                            overview = response.overview,
                            imagePath = response.posterPath.toString(),
                            rating = response.voteAverage
                        )
                        tvShowList.add(tvShow)
                    }
                }
                localDataSource.insertTvShowList(tvShowList)
            }

        }.asLiveData()
    }

    override fun getDetailTvShow(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowDetailResponse>(coroutineScope) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localDataSource.getTvShowDetail(id.toString())
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data != null && data.genres == ""


            override suspend fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> {
                return remoteDataSource.getDetailTvShow(id)
            }

            override suspend fun saveCallResult(data: TvShowDetailResponse?) {
                val genres = StringBuilder().append("")

                for (i in data?.genres?.indices!!) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }
                val detailTvShow = TvShowEntity(
                    id = data.id.toString(),
                    name  =data.name,
                    genres = genres.toString(),
                    overview = data.overview,
                    imagePath = data.posterPath,
                    rating = data.voteAverage,
                )
                localDataSource.updateTvShow(detailTvShow)
            }

        }.asLiveData()
    }

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteMovieList(), config).build()
    }
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoriteTvShowList(), config).build()
    }
}