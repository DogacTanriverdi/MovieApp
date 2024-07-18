package com.dogactanriverdi.movieapp.data.di

import com.dogactanriverdi.movieapp.domain.repository.MovieRepository
import com.dogactanriverdi.movieapp.domain.repository.PersonRepository
import com.dogactanriverdi.movieapp.domain.repository.SearchRepository
import com.dogactanriverdi.movieapp.domain.repository.TvSeriesRepository
import com.dogactanriverdi.movieapp.domain.usecase.movie.MovieCreditsUseCase
import com.dogactanriverdi.movieapp.domain.usecase.movie.MovieDetailUseCase
import com.dogactanriverdi.movieapp.domain.usecase.movie.MovieUseCases
import com.dogactanriverdi.movieapp.domain.usecase.movie.TrendingMoviesUseCase
import com.dogactanriverdi.movieapp.domain.usecase.movie.UpcomingMoviesUseCase
import com.dogactanriverdi.movieapp.domain.usecase.person.PersonDetailUseCase
import com.dogactanriverdi.movieapp.domain.usecase.person.PersonMovieCreditsUseCase
import com.dogactanriverdi.movieapp.domain.usecase.person.PersonTvSeriesCreditsUseCase
import com.dogactanriverdi.movieapp.domain.usecase.person.PersonUseCases
import com.dogactanriverdi.movieapp.domain.usecase.search.SearchUseCase
import com.dogactanriverdi.movieapp.domain.usecase.tvseries.TrendingTvSeriesUseCase
import com.dogactanriverdi.movieapp.domain.usecase.tvseries.TvSeriesCreditsUseCase
import com.dogactanriverdi.movieapp.domain.usecase.tvseries.TvSeriesDetailUseCase
import com.dogactanriverdi.movieapp.domain.usecase.tvseries.TvSeriesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideMovieUseCases(
        movieRepository: MovieRepository
    ): MovieUseCases {
        return MovieUseCases(
            trendingMovies = TrendingMoviesUseCase(movieRepository),
            upcomingMovies = UpcomingMoviesUseCase(movieRepository),
            movieDetail = MovieDetailUseCase(movieRepository),
            movieCredits = MovieCreditsUseCase(movieRepository)
        )
    }

    @Singleton
    @Provides
    fun provideTvSeriesUseCases(
        tvSeriesRepository: TvSeriesRepository
    ): TvSeriesUseCases {
        return TvSeriesUseCases(
            trendingTvSeries = TrendingTvSeriesUseCase(tvSeriesRepository),
            tvSeriesDetail = TvSeriesDetailUseCase(tvSeriesRepository),
            tvSeriesCredits = TvSeriesCreditsUseCase(tvSeriesRepository)
        )
    }

    @Singleton
    @Provides
    fun providePersonUseCases(
        personRepository: PersonRepository
    ): PersonUseCases {
        return PersonUseCases(
            personDetail = PersonDetailUseCase(personRepository),
            personMovieCredits = PersonMovieCreditsUseCase(personRepository),
            personTvSeriesCredits = PersonTvSeriesCreditsUseCase(personRepository)
        )
    }

    @Singleton
    @Provides
    fun provideSearchUseCase(
        searchRepository: SearchRepository
    ): SearchUseCase {
        return SearchUseCase(searchRepository)
    }
}