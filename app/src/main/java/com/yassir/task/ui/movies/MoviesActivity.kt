package com.yassir.task.ui.movies

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yassir.task.databinding.ActivityMoviesBinding
import com.yassir.task.ui.detail.DetailActivity
import com.yassir.task.ui.movies.adapter.MoviesAdapter
import com.yassir.task.ui.movies.adapter.MoviesLoadingStateAdapter
import com.yassir.task.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMoviesBinding
    private val viewModel: MoviesViewModel by viewModels()

    private lateinit var mAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        setAdapters()

        observeMovies()

    }

    private fun initBinding() {
        activityMainBinding = ActivityMoviesBinding.inflate(layoutInflater)
        val view = activityMainBinding.root
        setContentView(view)
    }

    private fun setAdapters() {
        activityMainBinding.moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MoviesActivity)
            setHasFixedSize(true)
            mAdapter = MoviesAdapter { navigateToDetailScreen(it) }
            adapter = mAdapter
        }

        activityMainBinding.moviesRecyclerView.adapter = mAdapter.withLoadStateFooter(
            footer = MoviesLoadingStateAdapter { mAdapter.retry() }
        )
    }

    private fun observeMovies() {
        lifecycleScope.launch {
            viewModel.getMovies()
                .collectLatest {
                    mAdapter.submitData(it)
                }
        }
    }

    private fun navigateToDetailScreen(id: Int) {
        val nextScreenIntent = Intent(this, DetailActivity::class.java).apply {
            putExtra(Constants.MOVIE_ID, id)
        }
        startActivity(nextScreenIntent)
    }

}