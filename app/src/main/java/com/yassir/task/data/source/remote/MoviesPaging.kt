package com.yassir.task.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yassir.task.data.dto.movie_list.Movie

class MoviesPaging(private val remoteData: RemoteData) :
    PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = remoteData.getMovies(page)
            LoadResult.Page(
                data = response.data?.results!!,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.data.results.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}