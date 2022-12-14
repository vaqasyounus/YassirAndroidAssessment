package com.yassir.task.data.dto.movie_list

import com.google.gson.annotations.SerializedName


data class Movies(

    @SerializedName("page") var page: Int? = null,

    @SerializedName("results") var results: List<Movie> = arrayListOf(),

    @SerializedName("total_pages") var totalPages: Int? = null,

    @SerializedName("total_results") var totalResults: Int? = null

)