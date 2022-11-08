package com.yassir.task.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.example.MovieDetail
import com.yassir.task.data.Resource
import com.yassir.task.data.dto.movie_list.Movies
import com.yassir.task.data.dto.movie_list.Movie
import com.yassir.task.data.source.remote.RemoteDataImp
import com.yassir.task.data.source.remote.MoviesPaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    private val remoteDataImp: RemoteDataImp,
) : MoviesRepository {

    override suspend fun getMovieDetail(movie_id: Int): Flow<Resource<MovieDetail>> {
        return flow {
            emit(remoteDataImp.getMovieDetail(movie_id))
        }.flowOn(Dispatchers.IO)

    }

    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MoviesPaging(remoteDataImp)
            }
        ).flow
    }


}