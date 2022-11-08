package com.yassir.task.data.source.remote

import com.example.example.MovieDetail
import com.yassir.task.data.Resource
import com.yassir.task.data.dto.movie_list.Movies
import com.yassir.task.utils.NetworkConnectivity
import retrofit2.Response
import javax.inject.Inject

class RemoteDataImp @Inject constructor(
    private val apiService: ApiService,
    private val networkConnectivity: NetworkConnectivity,
) : RemoteData {

    override suspend fun getMovies(page: Int): Resource<Movies> {
        return processResponse(apiService.fetchMovies(page))
    }

    override suspend fun getMovieDetail(movie_id: Int): Resource<MovieDetail> {
        return try {
            processResponse(apiService.fetchMovieDetail(movie_id))
        } catch (e: Exception) {
            return Resource.Error("Network Failure!")
        }
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