package com.yassir.task.data.repository

import com.example.example.MovieDetail
import com.yassir.task.data.Resource
import com.yassir.task.data.dto.movie_list.Movies
import kotlinx.coroutines.flow.Flow
import java.util.*

/**
 * To make an interaction between [PhotoRepositoryImp] & [GetPhotosUseCase]
 * */
interface MoviesRepository {
    suspend fun getMovies(): Flow<Resource<Movies>>
    suspend fun getMovieDetail(movie_id: Int): Flow<Resource<MovieDetail>>
}
