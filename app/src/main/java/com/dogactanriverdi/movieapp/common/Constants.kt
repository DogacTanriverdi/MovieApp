package com.dogactanriverdi.movieapp.common

object Constants {

    /* Base */
    const val BASE_URL = "https://api.themoviedb.org/3/"

    /* Endpoints */
    const val TRENDING_MOVIE_WEEK = "trending/movie/week"
    const val UPCOMING_MOVIES = "movie/upcoming"
    const val MOVIE_DETAIL = "movie/{movie_id}"
    const val MOVIE_CREDITS = "movie/{movie_id}/credits"

    const val TRENDING_TV_SERIES_WEEK = "trending/tv/week"
    const val TV_SERIES_DETAIL = "tv/{series_id}"
    const val TV_SERIES_CREDITS = "tv/{series_id}/credits"

    /* Queries */
    const val API_KEY = "api_key"
    const val PAGE = "page"
    const val LANGUAGE = "language"
    const val MOVIE_ID = "movie_id"
    const val TV_SERIES_ID = "series_id"
}