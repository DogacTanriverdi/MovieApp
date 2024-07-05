package com.dogactanriverdi.movieapp.data.source.remote.mapper.person

import com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.movie.PersonMovieCreditsCastDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.movie.PersonMovieCreditsCrewDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.movie.PersonMovieCreditsDto
import com.dogactanriverdi.movieapp.domain.model.person.credit.movie.PersonMovieCredits
import com.dogactanriverdi.movieapp.domain.model.person.credit.movie.PersonMovieCreditsCast
import com.dogactanriverdi.movieapp.domain.model.person.credit.movie.PersonMovieCreditsCrew

fun PersonMovieCreditsDto.toPersonMovieCredits(): PersonMovieCredits {
    return PersonMovieCredits(
        id = id ?: -1,
        cast = cast?.map { it.toPersonMovieCreditsCast() } ?: emptyList(),
        crew = crew?.map { it.toPersonMovieCreditsCrew() } ?: emptyList(),
    )
}

fun PersonMovieCreditsCastDto.toPersonMovieCreditsCast(): PersonMovieCreditsCast {
    return PersonMovieCreditsCast(
        id = id ?: -1,
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        character = character.orEmpty(),
        creditId = creditId.orEmpty(),
        genreIds = genreIds ?: emptyList(),
        order = order ?: -1,
        originalLanguage = originalLanguage.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: -1.0,
        posterPath = posterPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        title = title.orEmpty(),
        video = video ?: false,
        voteAverage = voteAverage ?: -1.0,
        voteCount = voteCount ?: -1,
    )
}

fun PersonMovieCreditsCrewDto.toPersonMovieCreditsCrew(): PersonMovieCreditsCrew {
    return PersonMovieCreditsCrew(
        id = id ?: -1,
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        creditId = creditId.orEmpty(),
        department = department.orEmpty(),
        genreIds = genreIds ?: emptyList(),
        job = job.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: -1.0,
        posterPath = posterPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        title = title.orEmpty(),
        video = video ?: false,
        voteAverage = voteAverage ?: -1.0,
        voteCount = voteCount ?: -1,
    )
}