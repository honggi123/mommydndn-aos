package com.mommydndn.app.ui.features.signup

import android.Manifest
import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.android.gms.location.FusedLocationProviderClient
import com.mommydndn.app.R
import com.mommydndn.app.data.model.map.LocationInfo
import com.mommydndn.app.data.model.common.TownSearchType
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.data.model.map.displayName
import com.mommydndn.app.ui.components.box.RadioListBox
import com.mommydndn.app.ui.components.box.SearchUnderHeader
import com.mommydndn.app.ui.components.inputfield.Searchbar
import com.mommydndn.app.ui.components.modal.TermsCheckListModal
import com.mommydndn.app.ui.theme.GreyOpacity400
import com.mommydndn.app.util.PermissionUtils
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LocationSearchScreen(
    navHostController: NavHostController,
    viewModel: SignUpViewModel,
    fusedLocationClient: FusedLocationProviderClient
) {

    val context = LocalContext.current

    val keyword by viewModel.keyword.collectAsState()
    val terms by viewModel.terms.collectAsState()
    val signUpInfo by viewModel.signUpInfo.collectAsState()
    val searchType by viewModel.searchType.collectAsState()

    val pagingItemsByKeyword = viewModel.searchedTownsFlow.collectAsLazyPagingItems()
    val pagingItemsByLocation = viewModel.searchedTownsByLocation.collectAsLazyPagingItems()

    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            Log.d("TownCheckScreen", "권한이 동의되었습니다.")
            searchNearLocations(fusedLocationClient, viewModel)
        } else {
        }
        Log.d("TownCheckScreen", "권한이 거부되었습니다.")
    }

    LaunchedEffect(Unit) {
        PermissionUtils.checkAndRequestPermissions(
            context,
            permissions,
            launcher,
            onPermissionGranted = {
                searchNearLocations(
                    fusedLocationClient,
                    viewModel
                )
            }
        )
    }

    val scaffoldState = rememberScaffoldState()
    val sheetState =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true,
            animationSpec = spring(
                dampingRatio = 0.85f,
                stiffness = 100f
            )
        )

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Column {
                Searchbar(
                    modifier = Modifier.fillMaxWidth(),
                    keyword = keyword,
                    onValueChange = { viewModel.setKeyword(it) },
                    clearAction = { viewModel.clearKeyword() },
                    placeHolderText = stringResource(R.string.searched_neighborhood),
                    backStackAction = { navHostController.popBackStack() },
                )
                SearchUnderHeader(
                    modifier = Modifier.fillMaxWidth(),
                    headerText = if (keyword.isBlank()) {
                        stringResource(R.string.search_location_keyword)
                    } else {
                        stringResource(R.string.result_searched_neighborhood, keyword)
                    },
                    searchAction = {
                        PermissionUtils.checkAndRequestPermissions(
                            context,
                            permissions,
                            launcher,
                            onPermissionGranted = {
                                searchNearLocations(
                                    fusedLocationClient,
                                    viewModel
                                )
                            }
                        )
                    })
            }
        },
        scaffoldState = scaffoldState,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            RadioListBox(
                pagingItems = if (searchType == TownSearchType.KEYWORD) pagingItemsByKeyword else pagingItemsByLocation,
                onItemClick = { emdItem ->
                    scope.launch { sheetState.show() }
                    viewModel.setEmdId(emdItem?.id)
                },
                itemNameDisplay = EmdItem::displayName
            )
        }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContentColor = Color.Transparent,
        sheetBackgroundColor = Color.Transparent,
        scrimColor = GreyOpacity400,
        sheetElevation = 0.dp,
        sheetContent = {
            TermsCheckListModal(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
                onDismiss = { scope.launch { sheetState.hide() } },
                onItemSelected = { index, isChecked ->
                    viewModel.setTermItemCheckedState(terms.get(index).termsId, isChecked)
                },
                onComplete = { viewModel.signUp(signUpInfo) },
                itemList = terms,
                titleCheckBoxText = stringResource(R.string.total_terms_agreement)
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        )
    }
}

private fun searchNearLocations(
    fusedLocationClient: FusedLocationProviderClient,
    viewModel: SignUpViewModel
) {
    try {
        fusedLocationClient.lastLocation.addOnSuccessListener {
            it?.let {
                viewModel.setLocationInfo(
                    LocationInfo(
                        latitude = it.latitude,
                        longitude = it.longitude
                    )
                )
            }
        }
    } catch (e: SecurityException) {
        Log.d("TownCheckScreen", e.stackTraceToString())
    }
}


