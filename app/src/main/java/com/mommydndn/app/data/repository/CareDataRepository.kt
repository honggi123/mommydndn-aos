package com.mommydndn.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mommydndn.app.data.model.care.SortingType
import com.mommydndn.app.data.network.model.care.GetCareJobOpeningListRequest
import com.mommydndn.app.data.network.model.care.PageMeta
import com.mommydndn.app.data.network.service.CareService
import com.mommydndn.app.data.source.paging.JobOpeningListPagingSource
import com.mommydndn.app.domain.model.care.CareJobOpening
import com.mommydndn.app.domain.repository.CareRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CareDataRepository @Inject constructor(
    private val careService: CareService
) : CareRepository {

    override fun getNearbyCareJobOpenings(
        latitude: Double,
        longitude: Double
    ): Flow<PagingData<CareJobOpening>> {
        val page = 1

        val pageSize = 20

        val request = GetCareJobOpeningListRequest(
            pageMeta = PageMeta(
                page = page,
                size = pageSize,
                requestedAt = System.currentTimeMillis(),
            ),
            keyword = null,
            neighborhoodId = -1,
            nearbyNeighborhoodDistance = -1,
            careTypes = emptyList(),
            daysOfWeek = emptyList(),
            orderBy = SortingType.LATEST,
            startTime = null,
            endTime = null,
            workPeriods = emptyList(),
        )

        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                JobOpeningListPagingSource(
                    request = request,
                    careService = careService
                )
            }
        ).flow
    }
}