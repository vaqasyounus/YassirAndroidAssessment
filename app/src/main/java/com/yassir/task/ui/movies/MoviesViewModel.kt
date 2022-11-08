package com.yassir.task.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yassir.task.data.Resource
import com.yassir.task.data.dto.movie_list.Movies
import com.yassir.task.data.dto.movie_list.Movie
import com.yassir.task.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {


    fun getMovies(): Flow<PagingData<Movie>> {
        return moviesRepository.getMovies().cachedIn(viewModelScope)
    }

}