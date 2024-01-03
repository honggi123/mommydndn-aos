package com.mommydndn.app.ui.deprecated.signup

import com.mommydndn.app.domain.model.location.CoordinatesInfo
import com.mommydndn.app.domain.model.location.SearchType
import kotlinx.coroutines.flow.MutableStateFlow

class SearchManager {

    val currentLocationFlow = MutableStateFlow(CoordinatesInfo(0.0, 0.0))
    val keywordFlow = MutableStateFlow<String>("")

    fun updateKeyword(keyword: String) {
        keywordFlow.value = keyword
    }

    fun updateMyLocation(coordinatesInfo: CoordinatesInfo) {
        currentLocationFlow.value = coordinatesInfo
    }

    fun clearKeyword() {
        keywordFlow.value = ""
    }
}