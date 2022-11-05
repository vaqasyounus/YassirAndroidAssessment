package com.yassir.task.data.source.remote

import com.example.example.MovieDetail
import com.yassir.task.data.Resource
import com.yassir.task.data.dto.movie_list.Movies
import retrofit2.Response
import javax.inject.Inject

class RemoteDataImp @Inject constructor(private val apiService: ApiService) : RemoteData {

    override suspend fun getMovies(): Resource<Movies> {
        return processResponse(apiService.fetchMovies())
    }

    override suspend fun getMovieDetail(movie_id: Int): Resource<MovieDetail> {
        return processResponse(apiService.fetchMovieDetail(movie_id))
    }

    private fun <T> processResponse(response: Response<T>): Resource<T> {
        return try {
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }

    }

}