package com.example.jetpack2.ui.content.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack2.R
import com.example.jetpack2.adapter.FavMovieAdapter
import com.example.jetpack2.adapter.FavTvShowAdapter
import com.example.jetpack2.utils.Status
import com.example.jetpack2.viewmodel.FilmViewModel
import com.example.jetpack2.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_fav_film.*

class FavFilmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fav_film, container, false)
    }

    private val movieViewModel by lazy {
        val viewModelFactory= activity?.application?.let {
            ViewModelFactory.getInstance(it)
        }
        ViewModelProviders.of(this,viewModelFactory).get(FilmViewModel::class.java)
    }

    private val tvShowViewModel by lazy {
        val viewModelFactory= activity?.application?.let {
            ViewModelFactory.getInstance(it)
        }
        ViewModelProviders.of(this,viewModelFactory).get(FilmViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = FavMovieAdapter(context)
        movieViewModel.getMoviesPaged.observe(viewLifecycleOwner, Observer { response ->

            if (response != null) {
                when (response.status) {
                    Status.LOADING -> { }
                    Status.SUCCESS -> {
                        movieAdapter.submitList(response.data)
                        movieAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Load Data Failed",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        rvMovieFav.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }

        val tvshowAdapter = context?.let { FavTvShowAdapter(it) }
        tvShowViewModel.getTvShowPaged.observe(viewLifecycleOwner, { response ->

            if (response != null) {
                when (response.status) {
                    Status.LOADING -> { }
                    Status.SUCCESS -> {
                        tvshowAdapter?.submitList(response.data)
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Load Data Failed",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })

        rvTvShowFav.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = tvshowAdapter
        }

    }

}