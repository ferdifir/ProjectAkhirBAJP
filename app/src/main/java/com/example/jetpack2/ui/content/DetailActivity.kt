package com.example.jetpack2.ui.content

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.jetpack2.R
import com.example.jetpack2.data.model.local.entity.MovieEntity
import com.example.jetpack2.data.model.local.entity.TvShowEntity
import com.example.jetpack2.utils.Helper.TYPE_MOVIE
import com.example.jetpack2.utils.Helper.TYPE_TVSHOW
import com.example.jetpack2.utils.Status
import com.example.jetpack2.viewmodel.FilmViewModel
import com.example.jetpack2.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "Detail Film"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setDetailFilm()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite_film, menu)
        DetailActivity().menu = menu
        val movie = intent.getIntExtra(TYPE_MOVIE, 0)

        if (movie != 0) {
            movieDetailViewModel?.getMovie!!.observe(this, { resource ->
                resource.let { it1 ->
                    when (it1.status) {
                        Status.LOADING -> { }
                        Status.SUCCESS -> {
                            it1.data?.let {
                                val state = it.favorite
                                setFavState(state)
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(applicationContext, "Load Data Failed",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        } else {
            tvShowDetailViewModel?.getTvShow!!.observe(this, { resource ->
                resource.let { it1 ->
                    when (it1.status) {
                        Status.LOADING -> { }
                        Status.SUCCESS -> {
                            it1.data?.let {
                                val state = it.favorite
                                setFavState(state)
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(applicationContext, "Load Data Failed",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
        return true
    }

    private fun setFavState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu!!.findItem(R.id.favorite_menu)
        if (state) {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_yes)
        } else {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_no)
        }

    }

    private fun setDetailFilm() {
        val movie = intent.getIntExtra(TYPE_MOVIE, 0)
        val tvshow = intent.getIntExtra(TYPE_TVSHOW, 0)

        if (movie != 0) {
            movieDetailViewModel?.getMovieDetail(movie)?.observe(this, {
                loadDataMovie(it)
                movieDetailViewModel!!.setMovieId(movie)
            })
        } else {
            tvShowDetailViewModel?.getTvShowDetail(tvshow)?.observe(this, {
                loadDataTvShow(it)
                movieDetailViewModel?.tvShowId?.value = tvshow
            })
        }
    }

    private val movieDetailViewModel by lazy {
        val viewModelFactory = ViewModelFactory.getInstance(application)
        viewModelFactory?.let { ViewModelProvider(this, it).get(FilmViewModel::class.java) }
    }

    private val tvShowDetailViewModel by lazy {
        val viewModelFactory = ViewModelFactory.getInstance(application)
        viewModelFactory?.let { ViewModelProvider(this, it).get(FilmViewModel::class.java) }
    }

    private fun loadDataTvShow(tvshow: TvShowEntity?) {
        if (tvshow != null) {
            Glide.with(this)
                .load(applicationContext.resources
                    .getIdentifier(tvshow
                        .tv_show_poster,
                        "drawable",
                        applicationContext.packageName))
                .into(img_item_photo)
        }
        tv_title.text = tvshow?.tv_show_title
        tv_desc.text = tvshow?.tv_show_description
        tv_realase_date.text = tvshow?.tv_show_release
        tv_orgin.text = tvshow?.tv_show_genre
    }

    private fun loadDataMovie(movie: MovieEntity?) {
        if (movie != null) {
            Glide.with(this)
                .load(applicationContext.resources
                    .getIdentifier(movie
                        .movie_poster,
                        "drawable",
                        applicationContext.packageName))
                .into(img_item_photo)
        }
        tv_title.text = movie?.movie_title
        tv_desc.text = movie?.movie_description
        tv_realase_date.text = movie?.movie_release
        tv_orgin.text = movie?.movie_genre
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorite_menu) {
            if (intent.getIntExtra(TYPE_MOVIE, 0) != 0) {
                movieDetailViewModel?.setFavoriteMovie()
            }else{
                tvShowDetailViewModel?.setFavoriteTvShow()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}