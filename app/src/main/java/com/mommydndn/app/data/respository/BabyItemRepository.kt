package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.BabyItem
import kotlinx.coroutines.flow.Flow

interface BabyItemRepository {
    fun fetchNearestBabyItem(): Flow<List<BabyItem>>

}