package com.alfian.moviecatalog3.data.source.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.alfian.moviecatalog3.data.source.local.LocalDataSource
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.data.source.remote.RemoteDataSource
import com.alfian.moviecatalog3.ui.helper.LiveDataTestUtil
import com.alfian.moviecatalog3.util.DataDummy
import com.alfian.moviecatalog3.utils.PagedListUtil
import com.alfian.moviecatalog3.utils.TestCoroutineRule
import com.alfian.moviecatalog3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class ShowRepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private var dummyDetailMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyDetailMovie.id
    private val dummyDetailTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyDetailTvShow.id

    @Mock
    private lateinit var remoteDataSource : RemoteDataSource

    @Mock
    private lateinit var localDataSource : LocalDataSource

    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var repo: FakeShowRepository

    @Before
    fun setUp(){
        repo = FakeShowRepository(remoteDataSource,localDataSource,coroutineScope)
    }

    @Test
    fun getAllMovies() = testCoroutineRule.runBlockingTest{
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getMovieList("Best")).thenReturn(dataSourceFactory)
        repo.getMovies("Best")

        val dataMovie = Resource.success(data = PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        Mockito.verify(localDataSource).getMovieList("Best")
        assertNotNull(dataMovie)
        assertEquals(DataDummy.generateDummyMovies().size, dataMovie.data?.size)
    }

    @Test
    fun getDetailMovie() = testCoroutineRule.runBlockingTest {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = dummyDetailMovie
        `when`(localDataSource.getMovieDetail(movieId)).thenReturn(dummyMovie)
        val movieDetailEntity = LiveDataTestUtil.getValue(repo.getDetailMovie(movieId.toInt()))
        verify(localDataSource).getMovieDetail(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(movieId, movieDetailEntity.data?.id)
        assertEquals(dummyDetailMovie.title, movieDetailEntity.data?.title)
        assertEquals(dummyDetailMovie.genres, movieDetailEntity.data?.genres)
        assertEquals(dummyDetailMovie.overview, movieDetailEntity.data?.overview)
        assertEquals(dummyDetailMovie.imagePath, movieDetailEntity.data?.imagePath)
        assertEquals(dummyDetailMovie.rating, movieDetailEntity.data?.rating)
    }

    @Test
    fun getAllTvShows() = testCoroutineRule.runBlockingTest{
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(localDataSource.getTvShowList("Best")).thenReturn(dataSourceFactory)
        repo.getTvShows("Best")

        val dataTvShows = Resource.success(data = PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        Mockito.verify(localDataSource).getTvShowList("Best")
        assertNotNull(dataTvShows)
        assertEquals(DataDummy.generateDummyTvShows().size, dataTvShows.data?.size)
    }
    @Test
    fun getDetailTvShows() = testCoroutineRule.runBlockingTest {
        val dummyTvShows = MutableLiveData<TvShowEntity>()
        dummyTvShows.value = dummyDetailTvShow
        `when`(localDataSource.getTvShowDetail(tvShowId)).thenReturn(dummyTvShows)
        val tvShowsDetailEntity = LiveDataTestUtil.getValue(repo.getDetailTvShow(tvShowId.toInt()))
        verify(localDataSource).getTvShowDetail(tvShowId)
        assertNotNull(tvShowsDetailEntity)
        assertEquals(tvShowId, tvShowsDetailEntity.data?.id)
        assertEquals(dummyDetailTvShow.name, tvShowsDetailEntity.data?.name)
        assertEquals(dummyDetailTvShow.genres, tvShowsDetailEntity.data?.genres)
        assertEquals(dummyDetailTvShow.overview, tvShowsDetailEntity.data?.overview)
        assertEquals(dummyDetailTvShow.imagePath, tvShowsDetailEntity.data?.imagePath)
        assertEquals(dummyDetailTvShow.rating, tvShowsDetailEntity.data?.rating)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>

        `when`(localDataSource.getFavoriteMovieList()).thenReturn(dataSourceFactory)
        repo.getFavoriteMovie()

        val dataMovie = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        Mockito.verify(localDataSource).getFavoriteMovieList()
        assertNotNull(dataMovie)
        assertEquals(DataDummy.generateDummyMovies().size, dataMovie.data?.size)
    }
    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>

        `when`(localDataSource.getFavoriteTvShowList()).thenReturn(dataSourceFactory)
        repo.getFavoriteTvShow()

        val dataTvShows = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        Mockito.verify(localDataSource).getFavoriteTvShowList()
        assertNotNull(dataTvShows)
        assertEquals(DataDummy.generateDummyTvShows().size, dataTvShows.data?.size)
    }

}