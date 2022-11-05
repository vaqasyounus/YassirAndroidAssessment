package com.yassir.task.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.yassir.task.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.getMovies()
        viewModel.getMovieDetail(663712)


        lifecycleScope.launchWhenStarted {
            viewModel.movies.collect{
               Log.d("","${ it.data?.totalPages}")
            }
        }

        lifecycleScope . launchWhenStarted {
            viewModel.movieDetail.collect {
                Log.d("", "${it.data?.adult}")
            }
        }

    }
}