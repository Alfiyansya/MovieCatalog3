package com.alfian.moviecatalog3.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.data.source.repo.ShowRepository
import com.alfian.moviecatalog3.util.DataDummy
import com.alfian.moviecatalog3.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]

    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    private lateinit var viewModel: DetailViewModel

    @Mock
    private lateinit var detailRepo: ShowRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(detailRepo)
    }

    @Test
    fun getDetailMovieTest() {
        val movie =  MutableLiveData<Resource<MovieEntity>>()
        val dummyMovie = Resource.success(dummyMovie)
        movie.value = dummyMovie

        Mockito.`when`(movieId.toInt().let { detailRepo.getDetailMovie(it) }).thenReturn(movie)

        val movieData = movieId.toInt().let { viewModel.getDetailMovie(it).value }

        Mockito.verify(detailRepo).getDetailMovie(movieId.toInt())

        assertNotNull(movie)
        assertEquals(dummyMovie.data?.id, movieData?.data?.id)
        assertEquals(dummyMovie.data?.title, movieData?.data?.title)
        assertEquals(dummyMovie.data?.genres, movieData?.data?.genres)
        assertEquals(dummyMovie.data?.overview, movieData?.data?.overview)
        assertEquals(dummyMovie.data?.imagePath, movieData?.data?.imagePath)
        assertEquals(dummyMovie.data?.backdropPath, movieData?.data?.backdropPath)
        assertEquals(dummyMovie.data?.rating, movieData?.data?.rating)
        viewModel.getDetailMovie(movieId.toInt()).observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getDetailTvShowTest() {
        val tvShow =  MutableLiveData<Resource<TvShowEntity>>()
        val dummyTvShow = Resource.success(dummyTvShow)
        tvShow.value = dummyTvShow


        Mockito.`when`(tvShowId.toInt().let { detailRepo.getDetailTvShow(it) }).thenReturn(tvShow)

        val tvShowData = tvShowId.toInt().let { viewModel.getDetailTvShow(it).value }

        Mockito.verify(detailRepo).getDetailTvShow(tvShowId.toInt())

        assertNotNull(tvShow)
        assertEquals(dummyTvShow.data?.id, tvShowData?.data?.id)
        assertEquals(dummyTvShow.data?.name, tvShowData?.data?.name)
        assertEquals(dummyTvShow.data?.overview, tvShowData?.data?.overview)
        assertEquals(dummyTvShow.data?.genres, tvShowData?.data?.genres)
        assertEquals(dummyTvShow.data?.imagePath, tvShowData?.data?.imagePath)
        assertEquals(dummyTvShow.data?.backdropPath, tvShowData?.data?.backdropPath)
        assertEquals(dummyTvShow.data?.rating, tvShowData?.data?.rating)
        viewModel.getDetailTvShow(tvShowId.toInt()).observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(dummyTvShow)
    }

}