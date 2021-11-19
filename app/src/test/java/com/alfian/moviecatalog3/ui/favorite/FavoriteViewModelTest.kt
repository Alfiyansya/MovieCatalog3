package com.alfian.moviecatalog3.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.data.source.repo.ShowRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest{
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var favoriteViewModel: FavoriteViewModel

    @Mock
    private lateinit var repo : ShowRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var tvShowPagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        favoriteViewModel = FavoriteViewModel(repo)
    }

    @Test
    fun getFavoriteMoviesList() {
        `when`(moviePagedList.size).thenReturn(10)
        val result = MutableLiveData<PagedList<MovieEntity>>()
        result.value = moviePagedList
        `when`(repo.getFavoriteMovie()).thenReturn(result)
        val dataResult = favoriteViewModel.getFavoriteMovie().value
        verify(repo).getFavoriteMovie()
        assertNotNull(dataResult)
        assertEquals(10,dataResult?.size)
        favoriteViewModel.getFavoriteMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(moviePagedList)
    }
    @Test
    fun getFavoriteTvShowsList() {
        `when`(tvShowPagedList.size).thenReturn(10)
        val result = MutableLiveData<PagedList<TvShowEntity>>()
        result.value = tvShowPagedList
        `when`(repo.getFavoriteTvShow()).thenReturn(result)
        val dataResult = favoriteViewModel.getFavoriteTvShow().value
        verify(repo).getFavoriteTvShow()
        assertNotNull(dataResult)
        assertEquals(10,dataResult?.size)
        favoriteViewModel.getFavoriteTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShowPagedList)
    }
}