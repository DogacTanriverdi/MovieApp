package com.dogactanriverdi.movieapp.data.source.remote.service

import com.dogactanriverdi.movieapp.BuildConfig
import com.dogactanriverdi.movieapp.common.Constants
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.credit.TvSeriesCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.trending.TrendingTvSeriesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvSeriesService {

    @GET(Constants.TRENDING_TV_SERIES_WEEK)
    suspend fun getTrendingTvSeries(
        @Query(Constants.PAGE) page: Int,
        @Query(Constants.LANGUAGE) language: String,
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.API_KEY
    ): TrendingTvSeriesDto

    @GET(Constants.TV_SERIES_DETAIL)
    suspend fun getTvSeriesDetail(
        @Path(Constants.TV_SERIES_ID) seriesId: Int,
        @Query(Constants.LANGUAGE) language: String,
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.API_KEY
    ): TvSeriesDetailDto

    @GET(Constants.TV_SERIES_CREDITS)
    suspend fun getTvSeriesCredits(
        @Path(Constants.TV_SERIES_ID) seriesId: Int,
        @Query(Constants.LANGUAGE) language: String,
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.API_KEY
    ): TvSeriesCreditsDto
}