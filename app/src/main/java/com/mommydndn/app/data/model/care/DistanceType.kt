package com.mommydndn.app.data.model.care

enum class DistanceType(val distantce: Int) {
    CLOSEST(1),
    NEAR(6),
    FAR(9),
    FURTHEST(24);

    companion object {
        fun find(distance: Int): DistanceType {
            return when {
                distance >= FURTHEST.distantce -> FURTHEST
                distance >= FAR.distantce -> FAR
                distance >= NEAR.distantce -> NEAR
                else -> CLOSEST
            }
        }
    }
}

data class DistanceTypeItem(
    val distantceType: DistanceType,
    val isSelected: Boolean = false
)