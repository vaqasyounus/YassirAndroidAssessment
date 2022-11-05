package com.yassir.task.di

import com.yassir.task.data.repository.MoviesRepositoryImp
import com.yassir.task.data.source.remote.RemoteDataImp
import com.yassir.task.data.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(
        remoteDataImp: RemoteDataImp
    ): MoviesRepository {
        return MoviesRepositoryImp(remoteDataImp)
    }

}