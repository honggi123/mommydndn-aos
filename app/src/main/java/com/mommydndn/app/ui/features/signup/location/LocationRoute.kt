package com.mommydndn.app.ui.features.signup.location

import android.content.Context
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mommydndn.app.domain.model.location.CoordinatesInfo
import com.mommydndn.app.domain.model.location.SearchType
import com.mommydndn.app.ui.features.signup.SignUpUiState
import com.mommydndn.app.ui.features.signup.SignUpViewModel
import com.mommydndn.app.util.PermissionUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun LocationRoute(
    onBackButtonClick: () -> Unit,
    onSignUpSuccess: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
        animationSpec = spring(
            dampingRatio = 0.85f,
            stiffness = 100f
        )
    )

    val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val (currentSearchType, updateSearchType) = rememberSaveable { mutableStateOf(SearchType.MY_LOCATION) }
    val pagingContents = rememberPagingItems(viewModel, onItemClick = { item ->
        scope.launch { sheetState.show() }
        viewModel.updateMyLocation(item)
    })

    val onSearchResult: () -> Unit = {
        searchNearByLocations(
            fusedLocationClient = fusedLocationClient,
            searchAction = { latitude, longitude ->
                viewModel.searchNearby(
                    coordinatesInfo = CoordinatesInfo(latitude = latitude, longitude = longitude)
                )
                updateSearchType(SearchType.MY_LOCATION)
            }
        )
    }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
            val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
            if (areGranted) {
                Log.d("LocationSearchRoute", "권한이 동의되었습니다.")
                onSearchResult()
            } else {
                Log.d("LocationSearchRoute", "권한이 거부되었습니다.")
            }
        }

    LaunchedEffect(Unit) {
        PermissionUtils.checkAndRequestPermissions(
            context,
            viewModel.permissions,
            launcher,
            onPermissionGranted = { onSearchResult() }
        )
    }

    val uiState by viewModel.locationSearchUiState.collectAsStateWithLifecycle()

    when (val uiState = uiState) {
        is SignUpUiState.LocationSearch.Loading -> {
            // TODO
        }

        is SignUpUiState.LocationSearch.Success -> {
            LocationScreen(
                uiState = uiState,
                modifier = Modifier.fillMaxSize(),
                currentSearchType = currentSearchType,
                pagingContents = pagingContents,
                onBackButtonClick = { onBackButtonClick() },
                onSearchClick = {
                    PermissionUtils.checkAndRequestPermissions(
                        context,
                        viewModel.permissions,
                        launcher,
                        onPermissionGranted = { onSearchResult() }
                    )
                },
                onDialogDismiss = { scope.launch { sheetState.hide() } },
                itemList = uiState.tosList,
                onDialogComplete = {
                    viewModel.signUp(
                        approvedTermsList = it,
                        allTermsList = uiState.tosList
                    )
                },
                scaffoldState = scaffoldState,
                sheetState = sheetState,
                onSearchTypeChange = { updateSearchType(it) },
                onClearClick = { viewModel.clearKeyword() },
                onKeywordChange = {
                    viewModel.search(it)
                    updateSearchType(SearchType.KEYWORD)
                }
            )
        }

        is SignUpUiState.LocationSearch.Failure -> {
            // TODO
        }

        is SignUpUiState.LocationSearch.SignUpSuccess -> {
            onSignUpSuccess()
        }
    }
}

private fun searchNearByLocations(
    fusedLocationClient: FusedLocationProviderClient,
    searchAction: (Double, Double) -> Unit,
) {
    try {
        fusedLocationClient.lastLocation.addOnSuccessListener {
            it?.let {
                searchAction(it.latitude, it.longitude)
            }
        }
    } catch (e: SecurityException) {
        Log.d("TownCheckScreen", e.stackTraceToString())
    }
}
