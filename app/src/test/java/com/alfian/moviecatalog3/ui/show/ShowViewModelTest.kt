package com.alfian.moviecatalog3.ui.show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.data.source.repo.ShowRepository
import com.alfian.moviecatalog3.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShowViewModelTest{
    private lateinit var showViewModel : ShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repo : ShowRepository

    @Mock
    private lateinit var pagedMovieList: PagedList<MovieEntity>
    @Mock
    private lateinit var pagedTvShowList: PagedList<TvShowEntity>

    @Mock
    private lateinit var movieObserver: Observer<Resource<PagedList<MovieEntity>>>
    @Mock
    private lateinit var tvShowObserver: Observer<Resource<PagedList<TvShowEntity>>>

    @Before
    fun setUp(){
        showViewModel = ShowViewModel(repo)
    }
    @Test
    fun getMovies(){
        val dummyMovie = Resource.success(pagedMovieList)

        `when`(dummyMovie.data?.size).thenReturn(10)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovie
        `when`(repo.getMovies("Best")).thenReturn(movies)
        val listMovie = showViewModel.getMovies("Best").value
        assertNotNull(listMovie)
        assertEquals(10, listMovie?.data?.size)
        Mockito.verify(repo).getMovies("Best")
        showViewModel.getMovies("Best").observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dummyMovie)
    }
    @Test
    fun getTvShows() {
        val dummyTvShow = Resource.success(pagedTvShowList)
        `when`(dummyTvShow.data?.size).thenReturn(10)
        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTvShow
        `when`(repo.getTvShows("Best")).thenReturn(tvShow)
        val listTvShow = showViewModel.getTvShows("Best").value
        assertNotNull(listTvShow)
        assertEquals(10, listTvShow?.data?.size)
        Mockito.verify(repo).getTvShows("Best")

        showViewModel.getTvShows("Best").observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(dummyTvShow)
    }
}