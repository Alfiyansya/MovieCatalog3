package com.alfian.moviecatalog3.ui.show

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.alfian.moviecatalog3.R
import com.alfian.moviecatalog3.data.source.local.entity.MovieEntity
import com.alfian.moviecatalog3.data.source.local.entity.TvShowEntity
import com.alfian.moviecatalog3.databinding.FragmentShowBinding
import com.alfian.moviecatalog3.ui.adapter.MovieAdapter
import com.alfian.moviecatalog3.ui.adapter.OnItemMovieClickCallback
import com.alfian.moviecatalog3.ui.adapter.OnItemTvShowClickCallback
import com.alfian.moviecatalog3.ui.adapter.TvShowAdapter
import com.alfian.moviecatalog3.ui.detail.DetailActivity
import com.alfian.moviecatalog3.util.DataDummy
import com.alfian.moviecatalog3.util.SortUtils.BEST_VOTE
import com.alfian.moviecatalog3.util.SortUtils.RANDOM_VOTE
import com.alfian.moviecatalog3.util.SortUtils.WORST_VOTE
import com.alfian.moviecatalog3.vo.Resource
import com.alfian.moviecatalog3.vo.Status
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("NotifyDataSetChanged")
class ShowFragment : Fragment(),Toolbar.OnMenuItemClickListener {
    private var _binding: FragmentShowBinding? = null
    private val binding get() = _binding
    private val showViewModel by viewModel<ShowViewModel>()
    private val movieAdapter: MovieAdapter by inject()
    private val tvShowAdapter: TvShowAdapter by inject()
    private var index = 0
    private var sort = BEST_VOTE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        // Inflate the layout for this fragment
        _binding = FragmentShowBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setUpToolbar()
        return binding?.root
    }
    private fun setUpToolbar() {
        binding?.showToolbar?.setOnMenuItemClickListener(this)
        binding?.showToolbar?.title = sort
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        index = arguments?.getInt(ARG_SECTION_NUMBER, 0)!!
        if (index == 1) {
            showViewModel.getMovies(sort).observe(viewLifecycleOwner, movieObserver)
        } else {
            showViewModel.getTvShows(sort).observe(viewLifecycleOwner,tvShowObserver)
        }
    }

    private val movieObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> showProgressBar(true)
                Status.SUCCESS -> {
                    showProgressBar(false)
                    movieAdapter.submitList(movies.data)
                    setUpRecyclerViewMovie()
                    movieAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showProgressBar(false)
                    Toast.makeText(context, R.string.terjadi_kesalahan, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    private val tvShowObserver = Observer<Resource<PagedList<TvShowEntity>>> { tvShows ->
        if (tvShows != null) {
            when (tvShows.status) {
                Status.LOADING -> showProgressBar(true)
                Status.SUCCESS -> {
                    showProgressBar(false)
                    tvShowAdapter.submitList(tvShows.data)
                    setUpRecyclerViewTv()
                    tvShowAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    showProgressBar(false)
                    Toast.makeText(context, R.string.terjadi_kesalahan, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    private fun showProgressBar(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setUpRecyclerViewMovie() {
        with(binding?.rvShow) {
            this?.layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = movieAdapter
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

    private fun setUpRecyclerViewTv() {
        with(binding?.rvShow) {
            this?.layoutManager =
                GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = tvShowAdapter
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

    private fun indexCheck(sort : String){
        if(index == 1){
            showViewModel.getMovies(sort).observe(viewLifecycleOwner, movieObserver)
        }else{
            showViewModel.getTvShows(sort).observe(viewLifecycleOwner, tvShowObserver)
        }
        binding?.showToolbar?.title = sort
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        activity?.menuInflater?.inflate(R.menu.filter_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onMenuItemClick(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.best_vote -> {
                sort = BEST_VOTE
                indexCheck(sort)
                item.isChecked = true
            }
            R.id.worst_vote -> {
                sort = WORST_VOTE
                indexCheck(sort)
                item.isChecked = true
            }
            R.id.random_vote -> {
                sort = RANDOM_VOTE
                indexCheck(sort)
                item.isChecked = true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section number"

        @JvmStatic
        fun newInstance(index: Int) =
            ShowFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, index)
                }
            }
    }



}