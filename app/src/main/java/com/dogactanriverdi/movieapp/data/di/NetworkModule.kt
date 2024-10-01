package com.dogactanriverdi.movieapp.data.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dogactanriverdi.movieapp.BuildConfig
import com.dogactanriverdi.movieapp.common.Constants.BASE_URL
import com.dogactanriverdi.movieapp.common.NetworkInterceptor
import com.dogactanriverdi.movieapp.data.source.remote.service.GenreService
import com.dogactanriverdi.movieapp.data.source.remote.service.MovieService
import com.dogactanriverdi.movieapp.data.source.remote.service.PersonService
import com.dogactanriverdi.movieapp.data.source.remote.service.SearchService
import com.dogactanriverdi.movieapp.data.source.remote.service.TvSeriesService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkInterceptor() = NetworkInterceptor()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    @[Provides Singleton]
    fun provideOkHttpClient(
        chuckInterceptor: ChuckerInterceptor,
        networkInterceptor: NetworkInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) addInterceptor(chuckInterceptor)
            addInterceptor(networkInterceptor)
            readTimeout(60L, TimeUnit.SECONDS)
            connectTimeout(60L, TimeUnit.SECONDS)
            writeTimeout(60L, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(application: Application) =
        ChuckerInterceptor.Builder(application).build()

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

    @Singleton
    @Provides
    fun providePersonService(retrofit: Retrofit): PersonService {
        return retrofit.create<PersonService>()
    }

    @Singleton
    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create<SearchService>()
    }

    @[Singleton Provides]
    fun provideGenreService(retrofit: Retrofit): GenreService {
        return retrofit.create<GenreService>()
    }
}