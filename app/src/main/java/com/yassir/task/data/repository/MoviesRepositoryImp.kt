package com.yassir.task.data.repository

import com.example.example.MovieDetail
import com.yassir.task.data.Resource
import com.yassir.task.data.source.remote.RemoteDataImp
import com.yassir.task.data.dto.movie_list.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    private val remoteDataImp: RemoteDataImp
) : MoviesRepository {

    override suspend fun getMovies(): Flow<Resource<Movies>> {
        return flow {
            emit(remoteDataImp.getMovies())
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun getMovieDetail(movie_id:Int): Flow<Resource<MovieDetail>> {
        return flow {
            emit(remoteDataImp.getMovieDetail(movie_id))
        }.flowOn(Dispatchers.IO)

    }


}