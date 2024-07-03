package com.dogactanriverdi.movieapp.data.repository

import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.credit.TvSeriesCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.trending.TrendingTvSeriesDto
import com.dogactanriverdi.movieapp.data.source.remote.service.TvSeriesService
import com.dogactanriverdi.movieapp.domain.repository.TvSeriesRepository
import javax.inject.Inject

class TvSeriesRepositoryImpl @Inject constructor(
    private val tvSeriesService: TvSeriesService
) : TvSeriesRepository {

    override suspend fun getTrendingTvSeries(page: Int, language: String): TrendingTvSeriesDto {
        return tvSeriesService.getTrendingTvSeries(page, language)
    }

    override suspend fun getTvSeriesDetail(seriesId: Int, language: String): TvSeriesDetailDto {
        return tvSeriesService.getTvSeriesDetail(seriesId, language)
    }

    override suspend fun getTvSeriesCredits(seriesId: Int, language: String): TvSeriesCreditsDto {
        return tvSeriesService.getTvSeriesCredits(seriesId, language)
    }
}