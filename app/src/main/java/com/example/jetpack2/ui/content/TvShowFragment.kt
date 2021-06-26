package com.example.jetpack2.ui.content

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack2.R
import com.example.jetpack2.adapter.TvShowAdapter
import com.example.jetpack2.data.model.local.entity.TvShowEntity
import com.example.jetpack2.utils.Helper.TYPE_TVSHOW
import com.example.jetpack2.viewmodel.FilmViewModel
import com.example.jetpack2.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment(), TvShowAdapter.OnItemClickCallback {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading(true)

        val movieAdapter =
            TvShowAdapter(context)

        movieAdapter.setOnItemClickCallback(this)
        movieViewModel?.tvShow?.observe(viewLifecycleOwner, {
            showLoading(false)
            movieAdapter.addList(it)
        })

        rv_tvshow.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pbTvShow.visibility = View.VISIBLE
        } else {
            pbTvShow.visibility = View.GONE
        }
    }

    private val movieViewModel by lazy {
        val viewModelFactory = activity?.application?.let {
            ViewModelFactory.getInstance(requireActivity().application)
        }
        viewModelFactory?.let { ViewModelProvider(this, it).get(FilmViewModel::class.java) }
    }

    override fun onItemClicked(data: TvShowEntity) {
        startActivity(
            Intent(context, DetailActivity::class.java)
                .putExtra(TYPE_TVSHOW, data.tv_show_id)
        )
    }
}