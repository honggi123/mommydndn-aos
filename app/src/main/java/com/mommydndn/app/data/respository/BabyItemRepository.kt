package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.BabyItem
import kotlinx.coroutines.flow.Flow

interface BabyItemRepository {
    fun fetchNearestBabyItem(
        onComplete: () -> Unit,
        onError: (message: String?) -> Unit
    ): Flow<List<BabyItem>>

}