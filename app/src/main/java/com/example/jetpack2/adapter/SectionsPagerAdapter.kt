package com.example.jetpack2.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jetpack2.ui.content.favorite.FavFilmFragment
import com.example.jetpack2.ui.content.MovieFragment
import com.example.jetpack2.ui.content.TvShowFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            2 -> FavFilmFragment()
            else -> MovieFragment()
        }
    }
}