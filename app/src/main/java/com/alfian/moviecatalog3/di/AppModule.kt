package com.alfian.moviecatalog3.di

import android.content.Context
import androidx.room.Room
import com.alfian.moviecatalog3.data.source.local.LocalDataSource
import com.alfian.moviecatalog3.data.source.local.ShowDataSourceImpl
import com.alfian.moviecatalog3.data.source.local.room.MovieDao
import com.alfian.moviecatalog3.data.source.local.room.ShowDatabase
import com.alfian.moviecatalog3.data.source.local.room.TvShowDao
import com.alfian.moviecatalog3.data.source.remote.RemoteDataSource
import com.alfian.moviecatalog3.data.source.remote.api.ApiBuilder
import com.alfian.moviecatalog3.data.source.remote.api.ApiService
import com.alfian.moviecatalog3.data.source.repo.ShowRepository
import com.alfian.moviecatalog3.network.NetworkConnection
import com.alfian.moviecatalog3.ui.adapter.MovieAdapter
import com.alfian.moviecatalog3.ui.adapter.TvShowAdapter
import com.alfian.moviecatalog3.ui.detail.DetailViewModel
import com.alfian.moviecatalog3.ui.favorite.FavoriteViewModel
import com.alfian.moviecatalog3.ui.show.ShowViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val showViewModelModule = module {
    viewModel { ShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}
val adapterModule = module {
    factory { MovieAdapter() }
    factory { TvShowAdapter() }
}
val remoteModule = module {
    single {
        RemoteDataSource(get())
    }
}
val repositoryModule = module {
    single {
        ShowRepository(get(), get(), get())
    }
}
val dataSourceModule = module {
    fun provideShowLocalDataSource(movieDao: MovieDao, tvShowDao: TvShowDao): LocalDataSource {
        return ShowDataSourceImpl(movieDao, tvShowDao)
    }
    single { provideShowLocalDataSource(get(), get()) }

}
val databaseModule = module {
    fun provideAppDatabase(context: Context): ShowDatabase {
        return Room.databaseBuilder(
            context,
            ShowDatabase::class.java,
            "show.db"
        ).fallbackToDestructiveMigration().build()
    }

    fun provideMovieDao(database: ShowDatabase): MovieDao {
        return database.movieDao()
    }

    fun provideTvShowDao(database: ShowDatabase): TvShowDao {
        return database.tvShowDao()
    }
    single { provideAppDatabase(androidApplication()) }
    single { provideMovieDao(get()) }
    single { provideTvShowDao(get()) }
}

val networkModule = module {
    fun provideNetworking(): ApiService {
        return ApiBuilder.createService()
    }
    single { provideNetworking() }
    single { NetworkConnection(androidApplication()) }

}
val coroutineScopeModule = module {
    single { CoroutineScope(Dispatchers.IO) }
}