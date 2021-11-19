package com.alfian.moviecatalog3.ui.detail

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alfian.moviecatalog3.R
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.databinding.ActivityDetailBinding
import com.alfian.moviecatalog3.network.NetworkConnection
import com.alfian.moviecatalog3.util.DataDummy
import com.alfian.moviecatalog3.util.DataDummy.IMAGE_ENDPOINT
import com.alfian.moviecatalog3.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding
    private val detailViewModel by viewModel<DetailViewModel>()
    private val networkConnection: NetworkConnection by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val id = intent.getStringExtra(EXTRA_ID)
        val key = intent.getStringExtra(EXTRA_KEY)
        checkInternetConnection(id, key)
    }

    private fun checkInternetConnection(id: String?, key: String?) {
        if (key == DataDummy.MOVIE) {
            showProgressBar(true)
            showDetailData(false)
            networkConnection.observe(this@DetailActivity) { isConnected ->
                if (isConnected) {
                    showProgressBar(false)
                    id?.toInt()?.let {
                        detailViewModel.getDetailMovie(it)
                            .observe(this@DetailActivity, { detailMovie ->
                                if (detailMovie != null) {
                                    when (detailMovie.status) {
                                        Status.LOADING -> showProgressBar(true)
                                        Status.SUCCESS -> {
                                            showProgressBar(false)
                                            showDetailData(true)
                                            setUpDataMovie(detailMovie.data)
                                            val isFavorite = !detailMovie.data?.isFavorite!!
                                            setUpFavMovieButton(detailMovie.data, isFavorite)
                                        }
                                        Status.ERROR -> {
                                            showProgressBar(false)
                                            showDetailData(false)
                                            Toast.makeText(
                                                this@DetailActivity,
                                                "Terjadi kesalahan",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }

                                }
                            })
                    }
                } else {
                    showProgressBar(false)
                    id?.toInt()?.let {
                        detailViewModel.getDetailMovie(it)
                            .observe(this@DetailActivity, { detailMovie ->
                                if (detailMovie != null) {
                                    when (detailMovie.status) {
                                        Status.LOADING -> showProgressBar(true)
                                        Status.SUCCESS -> {
                                            showProgressBar(false)
                                            showDetailData(true)
                                            setUpDataMovie(detailMovie.data)
                                            val isFavorite = !detailMovie.data?.isFavorite!!
                                            setUpFavMovieButton(detailMovie.data, isFavorite)
                                        }
                                        Status.ERROR -> {
                                            showProgressBar(false)
                                            showDetailData(false)
                                            binding?.detailProgressBar?.visibility = View.GONE
                                            Toast.makeText(
                                                this@DetailActivity,
                                                "Terjadi kesalahan",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }

                                }
                            })
                    }
                    Toast.makeText(
                        this@DetailActivity,
                        "Tidak ada koneksi internet",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }

        } else {
            showProgressBar(true)
            networkConnection.observe(this@DetailActivity) { isConnected ->
                if (isConnected) {
                    showProgressBar(false)
                    id?.toInt()?.let {
                        detailViewModel.getDetailTvShow(it)
                            .observe(this@DetailActivity, { detailTvShow ->
                                if (detailTvShow != null) {
                                    when (detailTvShow.status) {
                                        Status.LOADING -> showProgressBar(true)
                                        Status.SUCCESS -> {
                                            showProgressBar(false)
                                            showDetailData(true)
                                            setUpDataTvShow(detailTvShow.data)
                                            val isFavorite = !detailTvShow.data?.isFavorite!!
                                            setUpFavTvShowButton(detailTvShow.data, isFavorite)

                                        }
                                        Status.ERROR -> {
                                            showProgressBar(false)
                                            showDetailData(false)
                                            Toast.makeText(
                                                this@DetailActivity,
                                                R.string.terjadi_kesalahan,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }

                            })
                    }
                } else {
                    showProgressBar(false)
                    id?.toInt()?.let {
                        detailViewModel.getDetailTvShow(it)
                            .observe(this@DetailActivity, { detailTvShow ->
                                if (detailTvShow != null) {
                                    when (detailTvShow.status) {
                                        Status.LOADING -> showProgressBar(true)
                                        Status.SUCCESS -> {
                                            showProgressBar(false)
                                            showDetailData(true)
                                            setUpDataTvShow(detailTvShow.data)
                                            val isFavorite = !detailTvShow.data?.isFavorite!!
                                            setUpFavTvShowButton(detailTvShow.data, isFavorite)

                                        }
                                        Status.ERROR -> {
                                            showProgressBar(false)
                                            showDetailData(false)
                                            Toast.makeText(
                                                this@DetailActivity,
                                                R.string.terjadi_kesalahan,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }

                            })
                    }
                    Toast.makeText(
                        this@DetailActivity,
                        R.string.no_internet_connection,
                        Toast.LENGTH_LONG
                    ).show()

                }
            }

        }

    }

    private fun showProgressBar(isLoading: Boolean) {
        binding?.detailProgressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showDetailData(isAppears: Boolean) {
        binding?.detailData?.visibility = if (isAppears) View.VISIBLE else View.GONE
    }


    private fun setUpFavMovieButton(movieEntity: MovieEntity?, state: Boolean) {
        binding?.detailFabFavorite?.imageTintList =
            if (state) {
                ColorStateList.valueOf(Color.rgb(255, 255, 255))
            } else {
                ColorStateList.valueOf(Color.rgb(247, 106, 123))
            }
        binding?.detailFabFavorite?.apply {
            setOnClickListener {
                if (state) {
                    if (movieEntity != null) {
                        detailViewModel.setFavoriteMovie(movieEntity, state)
                    }
                    Toast.makeText(
                        this@DetailActivity,
                        " ${movieEntity?.title} telah ditambahkan ke data Favorite ",

                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (movieEntity != null) {
                        detailViewModel.setFavoriteMovie(movieEntity, false)
                    }
                    Toast.makeText(
                        this@DetailActivity,
                        "${movieEntity?.title} telah dihapus dari data Favorite ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setUpFavTvShowButton(tvShowEntity: TvShowEntity?, state: Boolean) {
        binding?.detailFabFavorite?.imageTintList =
            if (state) {
                ColorStateList.valueOf(Color.rgb(255, 255, 255))
            } else {
                ColorStateList.valueOf(Color.rgb(247, 106, 123))
            }
        binding?.detailFabFavorite?.apply {
            setOnClickListener {
                if (state) {
                    if (tvShowEntity != null) {
                        detailViewModel.setFavoriteTvShow(tvShowEntity, state)
                    }
                    Toast.makeText(
                        this@DetailActivity,
                        " ${tvShowEntity?.name} telah ditambahkan ke data Favorite ",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    if (tvShowEntity != null) {
                        detailViewModel.setFavoriteTvShow(tvShowEntity, false)
                    }
                    Toast.makeText(
                        this@DetailActivity,
                        " ${tvShowEntity?.name} telah dhapus dari data Favorite ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setUpDataMovie(movieEntity: MovieEntity?) {
        with(binding) {
            this?.detailImage?.let {
                Glide.with(this@DetailActivity)
                    .load(IMAGE_ENDPOINT + movieEntity?.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(it)
            }
            this?.detailPoster?.let {
                Glide.with(this@DetailActivity)
                    .load(IMAGE_ENDPOINT + movieEntity?.backdropPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(it)
            }
            this?.detailTitle?.text = movieEntity?.title
            this?.detailGenre?.text = movieEntity?.genres
            this?.detailRating?.text = movieEntity?.rating.toString()
            this?.detailOverviewValue?.text = movieEntity?.overview
            this?.detailRatingBar?.rating = (movieEntity?.rating?.toFloat()?.div(2)!!)
        }
    }

    private fun setUpDataTvShow(tvShowEntity: TvShowEntity?) {
        with(binding) {
            this?.detailImage?.let {
                Glide.with(this@DetailActivity)
                    .load(IMAGE_ENDPOINT + tvShowEntity?.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(it)
            }
            this?.detailPoster?.let {
                Glide.with(this@DetailActivity)
                    .load(IMAGE_ENDPOINT + tvShowEntity?.backdropPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(it)
            }

            this?.detailTitle?.text = tvShowEntity?.name
            this?.detailGenre?.text = tvShowEntity?.genres
            this?.detailRating?.text = tvShowEntity?.rating.toString()
            this?.detailOverviewValue?.text = tvShowEntity?.overview.toString()
            this?.detailRatingBar?.rating = (tvShowEntity?.rating?.toFloat()?.div(2)!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val EXTRA_ID = "id"
        const val EXTRA_KEY = "key"
    }
}