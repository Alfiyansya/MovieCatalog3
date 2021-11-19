package com.alfian.moviecatalog3.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alfian.moviecatalog3.ui.favorite.fragment.FavoriteFragment

class FavSectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return FavoriteFragment.newInstance(position + 1)
    }

}