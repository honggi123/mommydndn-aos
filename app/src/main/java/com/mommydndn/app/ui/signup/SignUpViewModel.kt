package com.mommydndn.app.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mommydndn.app.domain.model.Coordinates
import com.mommydndn.app.domain.model.Neighborhood
import com.mommydndn.app.domain.model.TermsOfService
import com.mommydndn.app.domain.usecase.invoke
import com.mommydndn.app.domain.usecase.location.SearchNeighborhoodByCoordinatesUseCase
import com.mommydndn.app.domain.usecase.location.SearchNeighborhoodByQueryUseCase
import com.mommydndn.app.domain.usecase.tos.GetTermsOfServiceUseCase
import com.mommydndn.app.domain.usecase.tos.UpdateTermsOfServiceStateUseCase
import com.mommydndn.app.domain.usecase.user.SignUpUseCase
import com.mommydndn.app.utils.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val searchNeighborhoodByQueryUseCase: SearchNeighborhoodByQueryUseCase,
    private val searchNeighborhoodByCoordinatesUseCase: SearchNeighborhoodByCoordinatesUseCase,
    private val getTermsOfServiceUseCase: GetTermsOfServiceUseCase,
    private val updateTermsOfServiceStateUseCase: UpdateTermsOfServiceStateUseCase,
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {

    private val _selectedUserType = MutableStateFlow<UserType?>(null)
    val selectedUserType = _selectedUserType.asStateFlow()

    fun setUserType(userType: UserType) {
        _selectedUserType.value = userType
    }

    private val query = MutableStateFlow<String?>(null)

    private val searchByQueryResult = query.filterNotNull()
        .filter { it.isNotEmpty() }
        // TODO
        .debounce(500.milliseconds)
        .flatMapLatest { query -> searchNeighborhoodByQueryUseCase(query) }
        .conflate()

    private val coordinates = MutableStateFlow<Coordinates?>(null)

    private val searchByCoordinatesResult = coordinates.filterNotNull()
        .flatMapConcat { coordinates -> searchNeighborhoodByCoordinatesUseCase(coordinates) }

    // TODO
    val searchResults = flowOf(searchByQueryResult, searchByCoordinatesResult)
        .flattenConcat()
        .filterIsInstance<Result.Success<PagingData<Neighborhood>>>()
        .map { result -> result.data }
        .cachedIn(viewModelScope)


    fun searchByQuery(query: String) {
        this.query.value = query
    }

    fun searchByCurrentLocation(coordinates: Coordinates) {
        this.coordinates.value = coordinates
    }

    private val _selectedNeighborhood = MutableStateFlow<Neighborhood?>(null)
    val selectedNeighborhood = _selectedNeighborhood.asStateFlow()

    fun setNeighborhood(neighborhood: Neighborhood) {
        _selectedNeighborhood.value = neighborhood
    }

    private val _termsOfService = MutableStateFlow<Map<TermsOfService, Boolean>>(emptyMap())
    val termsOfService = _termsOfService.asStateFlow()

    fun getTermsOfService() {
        viewModelScope.launch {
            when (val result = getTermsOfServiceUseCase()) {
                is Result.Success -> {
                    _termsOfService.value = result.data.associateWith { true }
                }
                is Result.Failure -> {
                    // TODO
                }
                else -> throw IllegalAccessException()
            }
        }
    }

    fun setTermsOfService(termsOfService: Map<TermsOfService, Boolean>) {
        _termsOfService.value = termsOfService
    }
}