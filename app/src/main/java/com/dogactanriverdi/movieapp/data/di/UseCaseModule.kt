package com.dogactanriverdi.movieapp.data.di

import com.dogactanriverdi.movieapp.domain.repository.GenreRepository
import com.dogactanriverdi.movieapp.domain.repository.MovieRepository
import com.dogactanriverdi.movieapp.domain.repository.PersonRepository
import com.dogactanriverdi.movieapp.domain.repository.SearchRepository
import com.dogactanriverdi.movieapp.domain.repository.TvSeriesRepository
import com.dogactanriverdi.movieapp.domain.repository.WatchListRepository
import com.dogactanriverdi.movieapp.domain.usecase.genre.GenreUseCases
import com.dogactanriverdi.movieapp.domain.usecase.genre.MovieGenreUseCase
import com.dogactanriverdi.movieapp.domain.usecase.genre.TvSeriesGenreUseCase
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
import com.dogactanriverdi.movieapp.domain.usecase.watchlist.AddToWatchListUseCase
import com.dogactanriverdi.movieapp.domain.usecase.watchlist.DeleteFromWatchListUseCase
import com.dogactanriverdi.movieapp.domain.usecase.watchlist.GetAllWatchListUseCase
import com.dogactanriverdi.movieapp.domain.usecase.watchlist.WatchListUseCases
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

    @[Singleton Provides]
    fun provideGenreUseCases(
        genreRepository: GenreRepository
    ): GenreUseCases {
        return GenreUseCases(
            movieGenre = MovieGenreUseCase(genreRepository),
            tvSeriesGenre = TvSeriesGenreUseCase(genreRepository)
        )
    }

    @[Provides Singleton]
    fun provideWatchListUseCases(
        watchListRepository: WatchListRepository
    ): WatchListUseCases {
        return WatchListUseCases(
            addToWatchList = AddToWatchListUseCase(watchListRepository),
            deleteFromWatchList = DeleteFromWatchListUseCase(watchListRepository),
            getAllWatchList = GetAllWatchListUseCase(watchListRepository)
        )
    }
}