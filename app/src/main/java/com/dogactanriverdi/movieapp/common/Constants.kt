package com.dogactanriverdi.movieapp.common

object Constants {

    /* Base */
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_IMAGE_URL_500 = "https://image.tmdb.org/t/p/w500/"
    const val BASE_IMAGE_URL_ORIGINAL = "https://image.tmdb.org/t/p/original/"

    /* Endpoints */
    const val TRENDING_MOVIE_WEEK = "trending/movie/week"
    const val UPCOMING_MOVIES = "movie/upcoming"
    const val MOVIE_DETAIL = "movie/{movie_id}"
    const val MOVIE_CREDITS = "movie/{movie_id}/credits"
    const val DISCOVER_MOVIE = "discover/movie"

    const val TRENDING_TV_SERIES_WEEK = "trending/tv/week"
    const val TV_SERIES_DETAIL = "tv/{series_id}"
    const val TV_SERIES_CREDITS = "tv/{series_id}/credits"
    const val DISCOVER_TV_SERIES = "discover/tv"

    const val PERSON_DETAIL = "person/{person_id}"
    const val PERSON_MOVIE_CREDITS = "person/{person_id}/movie_credits"
    const val PERSON_TV_SERIES_CREDITS = "person/{person_id}/tv_credits"

    const val SEARCH_MULTI = "search/multi"

    /* Queries */
    const val API_KEY = "api_key"
    const val PAGE = "page"
    const val LANGUAGE = "language"
    const val MOVIE_ID = "movie_id"
    const val TV_SERIES_ID = "series_id"
    const val PERSON_ID = "person_id"
    const val QUERY = "query"
    const val INCLUDE_ADULT = "include_adult"
    const val WITH_GENRES = "with_genres"

    /* Local Database */
    const val TABLE_NAME = "watch_list"
    const val DATABASE_NAME = "watch_list_database"
}