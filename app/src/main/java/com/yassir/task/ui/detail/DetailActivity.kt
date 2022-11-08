package com.yassir.task.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.yassir.task.data.Resource
import com.yassir.task.databinding.ActivityDetailBinding
import com.yassir.task.utils.Constants
import com.yassir.task.utils.loadImage
import com.yassir.task.utils.toGone
import com.yassir.task.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        getMovieDetail()

        observeDetail()

    }


    private fun initBinding() {
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        val view = activityDetailBinding.root
        setContentView(view)
    }

    private fun getMovieDetail() {
        viewModel.getMovieDetail(intent.getIntExtra(Constants.MOVIE_ID, 0))
    }

    private fun observeDetail() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.movieDetail.collect {
                when (it) {
                    is Resource.Loading -> activityDetailBinding.loaderView.toVisible()

                    is Resource.Success -> {

                        activityDetailBinding.loaderView.toGone()

                        it.data?.backdropPath?.let { path ->
                            activityDetailBinding.ivBanner.loadImage(path)
                        }

                        activityDetailBinding.detail.text = it.data?.overview
                    }
                    is Resource.Error -> {
                        activityDetailBinding.loaderView.toGone()
                        Toast.makeText(this@DetailActivity, it.error.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }
}