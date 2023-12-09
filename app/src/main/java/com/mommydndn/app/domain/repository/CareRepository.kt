package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.care.CareJobOpening
import kotlinx.coroutines.flow.Flow

interface CareRepository {

    fun getNearbyCareJobOpenings(latitude: Double, longitude: Double): Flow<List<CareJobOpening>>
}