package com.alfian.moviecatalog3.ui.favorite.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.databinding.FragmentFavoriteBinding
import com.alfian.moviecatalog3.ui.adapter.MovieAdapter
import com.alfian.moviecatalog3.ui.adapter.OnItemMovieClickCallback
import com.alfian.moviecatalog3.ui.adapter.OnItemTvShowClickCallback
import com.alfian.moviecatalog3.ui.adapter.TvShowAdapter
import com.alfian.moviecatalog3.ui.detail.DetailActivity
import com.alfian.moviecatalog3.ui.favorite.FavoriteViewModel
import com.alfian.moviecatalog3.util.DataDummy
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding
    private val favViewModel by viewModel<FavoriteViewModel>()
    private val movieAdapter: MovieAdapter by inject()
    private val tvShowAdapter: TvShowAdapter by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        if (index == 1) {
            favViewModel.getFavoriteMovie().observe(viewLifecycleOwner) { favMovie ->
                setUpRecyclerViewFavMovie()
                movieAdapter.submitList(favMovie)
                movieAdapter.notifyDataSetChanged()

            }
        } else {
            favViewModel.getFavoriteTvShow().observe(viewLifecycleOwner) { favTvShow ->
                setUpRecyclerViewFavTv()
                tvShowAdapter.submitList(favTvShow)
                movieAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setUpRecyclerViewFavMovie() {
        with(binding?.rvFavorite) {
            this?.adapter = movieAdapter
            this?.layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            this?.setHasFixedSize(true)
        }
        movieAdapter.setOnItemClickCallback(object : OnItemMovieClickCallback {
            override fun onItemClicked(movieEntity: MovieEntity?) {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID, movieEntity?.id)
                intent.putExtra(DetailActivity.EXTRA_KEY, DataDummy.MOVIE)
                startActivity(intent)
            }

        })
    }

    private fun setUpRecyclerViewFavTv() {
        with(binding?.rvFavorite) {
            this?.adapter = tvShowAdapter
            this?.layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            this?.setHasFixedSize(true)
        }
        tvShowAdapter.setOnItemClickCallback(object : OnItemTvShowClickCallback {
            override fun onItemClicked(tvShowEntity: TvShowEntity?) {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID, tvShowEntity?.id)
                intent.putExtra(DetailActivity.EXTRA_KEY, DataDummy.TVSHOW)
                startActivity(intent)
            }

        })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section number"

        @JvmStatic
        fun newInstance(index: Int) =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }

}