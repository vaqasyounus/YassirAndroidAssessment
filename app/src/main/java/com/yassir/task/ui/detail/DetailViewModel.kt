package com.yassir.task.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.example.MovieDetail
import com.yassir.task.data.Resource
import com.yassir.task.data.dto.movie_list.Movies
import com.yassir.task.data.dto.movie_list.Movie
import com.yassir.task.data.repository.MoviesRepository
import com.yassir.task.data.source.remote.RemoteDataImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val movieDetailPrivate = MutableSharedFlow<Resource<MovieDetail>>()
    val movieDetail: SharedFlow<Resource<MovieDetail>> get() = movieDetailPrivate


    fun getMovieDetail(movie_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetailPrivate.emit(Resource.Loading())
            moviesRepository.getMovieDetail(movie_id).collect {
                movieDetailPrivate.emit(it)
            }
        }
    }

}