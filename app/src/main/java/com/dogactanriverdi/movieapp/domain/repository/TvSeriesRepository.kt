package com.dogactanriverdi.movieapp.domain.repository

import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.credit.TvSeriesCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.trending.TrendingTvSeriesDto

interface TvSeriesRepository {

    suspend fun getTrendingTvSeries(page: Int, language: String): TrendingTvSeriesDto

    suspend fun getTvSeriesDetail(seriesId: Int, language: String): TvSeriesDetailDto

    suspend fun getTvSeriesCredits(seriesId: Int, language: String): TvSeriesCreditsDto
}