package com.yassir.task.data.source.remote

import com.example.example.MovieDetail
import com.yassir.task.data.dto.movie_list.Movies
import com.yassir.task.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface ApiService {

    @GET(Constants.END_POINT_MOVIES)
    suspend fun fetchMovies(): Response<Movies>

    @GET(Constants.END_POINT_MOVIE_DETAIL)
    suspend fun fetchMovieDetail(@Path(value = "movie_id") movie_id : Int): Response<MovieDetail>

}
