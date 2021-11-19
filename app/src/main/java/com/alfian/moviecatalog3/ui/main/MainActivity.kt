package com.alfian.moviecatalog3.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.alfian.moviecatalog3.R
import com.alfian.moviecatalog3.databinding.ActivityMainBinding
import com.alfian.moviecatalog3.ui.adapter.SectionPagerAdapter
import com.alfian.moviecatalog3.ui.favorite.activity.FavoriteActivity
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener  {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpToolbar()
        setUpLayout()
    }
    private fun setUpToolbar() {
        binding?.toolbar?.setOnMenuItemClickListener(this)
    }
    private fun setUpLayout(){
        val sectionPagerAdapter = SectionPagerAdapter(this@MainActivity)
        binding?.viewPager?.adapter = sectionPagerAdapter
        binding?.viewPager?.let {
            binding?.tabLayout?.let { it1 ->
                TabLayoutMediator(it1, it) { tab, position ->
                    tab.text = resources.getString(TAB_TITLES[position])
                }.attach()
            }
        }
        supportActionBar?.elevation = 0f

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.btn_favorite ->{
                val favorite = Intent(this, FavoriteActivity::class.java)
                startActivity(favorite)
                true
            }
            else -> false
        }
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