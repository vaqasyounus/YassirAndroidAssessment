package com.yassir.task.utils

import com.yassir.task.BuildConfig


/**
 * Contains fixed values that remains during execution once it is initialized
 */
object Constants {

    const val BASE_URL = BuildConfig.BASE_URL
    private const val API_KEY = BuildConfig.API_KEY
    const val END_POINT_MOVIES = "discover/movie?api_key=$API_KEY"
    const val END_POINT_MOVIE_DETAIL = "movie/{movie_id}}?api_key=$API_KEY"

}