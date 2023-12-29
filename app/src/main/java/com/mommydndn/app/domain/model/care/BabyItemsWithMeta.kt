<<<<<<<< HEAD:app/src/main/java/com/mommydndn/app/domain/model/care/BabyItemsWithMeta.kt
package com.mommydndn.app.domain.model.care
========
package com.mommydndn.app.data.network.model.response
>>>>>>>> refactor/code_care:app/src/main/java/com/mommydndn/app/data/network/model/response/BabyItemSummaryResponse.kt

data class BabyItemsWithMeta(
    val itemSummaryList: List<BabyItem>,
    val meta: BabyItemMeta
)
data class BabyItem(
    val itemId: Int,
    val imageUrl: String,
    val price: Int,
    val title: String,
    val neighborhood: String,
    val createdAt: Long,
    val isLiked: Boolean
)

data class BabyItemMeta(
    val totalCount: Int,
    val currentPageNum: Int,
    val requestTimestamp: Long
)


