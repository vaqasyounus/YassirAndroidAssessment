package com.yassir.task.data.source.remote

import com.example.example.MovieDetail
import com.yassir.task.data.Resource
import com.yassir.task.data.dto.movie_list.Movies
import java.util.*

/**
 * To make an interaction between [PhotoRepositoryImp] & [GetPhotosUseCase]
 * */
interface RemoteData {
    suspend fun getMovies(): Resource<Movies>
    suspend fun getMovieDetail(movie_id: Int): Resource<MovieDetail>
}
