package com.dogactanriverdi.movieapp.data.di

import com.dogactanriverdi.movieapp.data.repository.GenreRepositoryImpl
import com.dogactanriverdi.movieapp.data.repository.MovieRepositoryImpl
import com.dogactanriverdi.movieapp.data.repository.PersonRepositoryImpl
import com.dogactanriverdi.movieapp.data.repository.SearchRepositoryImpl
import com.dogactanriverdi.movieapp.data.repository.TvSeriesRepositoryImpl
import com.dogactanriverdi.movieapp.data.source.remote.service.GenreService
import com.dogactanriverdi.movieapp.data.source.remote.service.MovieService
import com.dogactanriverdi.movieapp.data.source.remote.service.PersonService
import com.dogactanriverdi.movieapp.data.source.remote.service.SearchService
import com.dogactanriverdi.movieapp.data.source.remote.service.TvSeriesService
import com.dogactanriverdi.movieapp.domain.repository.GenreRepository
import com.dogactanriverdi.movieapp.domain.repository.MovieRepository
import com.dogactanriverdi.movieapp.domain.repository.PersonRepository
import com.dogactanriverdi.movieapp.domain.repository.SearchRepository
import com.dogactanriverdi.movieapp.domain.repository.TvSeriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieService: MovieService,
    ): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }

    @Singleton
    @Provides
    fun provideTvSeriesRepository(
        tvSeriesService: TvSeriesService
    ): TvSeriesRepository {
        return TvSeriesRepositoryImpl(tvSeriesService)
    }

    @Singleton
    @Provides
    fun providePersonRepository(
        personService: PersonService
    ): PersonRepository {
        return PersonRepositoryImpl(personService)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(
        searchService: SearchService
    ): SearchRepository {
        return SearchRepositoryImpl(searchService)
    }

    @[Singleton Provides]
    fun provideGenreRepository(
        genreService: GenreService
    ): GenreRepository {
        return GenreRepositoryImpl(genreService)
    }
}