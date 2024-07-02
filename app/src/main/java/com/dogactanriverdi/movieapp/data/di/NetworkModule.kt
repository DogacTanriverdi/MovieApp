package com.dogactanriverdi.movieapp.data.di

import com.dogactanriverdi.movieapp.common.Constants.BASE_URL
import com.dogactanriverdi.movieapp.data.source.remote.service.MovieService
import com.dogactanriverdi.movieapp.data.source.remote.service.TvSeriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create<MovieService>()
    }

    @Singleton
    @Provides
    fun provideTvSeriesService(retrofit: Retrofit): TvSeriesService {
        return retrofit.create<TvSeriesService>()
    }
}