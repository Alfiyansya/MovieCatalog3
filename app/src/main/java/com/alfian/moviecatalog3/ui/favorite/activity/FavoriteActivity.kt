package com.alfian.moviecatalog3.ui.favorite.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.alfian.moviecatalog3.R
import com.alfian.moviecatalog3.databinding.ActivityFavoriteBinding
import com.alfian.moviecatalog3.ui.adapter.FavSectionPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteActivity : AppCompatActivity() {
    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpLayout()
    }

    private fun setUpLayout() {
        val favSectionPagerAdapter = FavSectionPagerAdapter(this@FavoriteActivity)
        binding?.favViewPager?.adapter = favSectionPagerAdapter
        binding?.favViewPager?.let {
            binding?.favTabLayout?.let { it1 ->
                TabLayoutMediator(it1, it) { tab, position ->
                    tab.text = resources.getString(TAB_TITLES[position])
                }.attach()
            }
        }
        supportActionBar?.elevation = 0f

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}