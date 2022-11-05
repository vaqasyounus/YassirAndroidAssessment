package com.yassir.task.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.MovieDetail
import com.yassir.task.data.Resource
import com.yassir.task.data.dto.movie_list.Movies
import com.yassir.task.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val moviesPrivate = MutableSharedFlow<Resource<Movies>>()
    val movies: SharedFlow<Resource<Movies>> get() = moviesPrivate


    private val movieDetailPrivate = MutableSharedFlow<Resource<MovieDetail>>()
    val movieDetail: SharedFlow<Resource<MovieDetail>> get() = movieDetailPrivate


    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            moviesPrivate.emit(Resource.Loading())
            moviesRepository.getMovies().collect {
                moviesPrivate.emit(it)
            }
        }
    }

    fun getMovieDetail(movie_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetailPrivate.emit(Resource.Loading())
            moviesRepository.getMovieDetail(movie_id).collect {
                movieDetailPrivate.emit(it)
            }
        }
    }
}