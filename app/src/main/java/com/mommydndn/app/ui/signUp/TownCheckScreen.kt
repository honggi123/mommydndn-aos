package com.mommydndn.app.ui.signUp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.android.gms.location.FusedLocationProviderClient
import com.mommydndn.app.data.model.EmdItem
import com.mommydndn.app.data.model.LocationInfo
import com.mommydndn.app.data.model.NearestSearchType
import com.mommydndn.app.data.model.displayName
import com.mommydndn.app.ui.component.RadioListBox
import com.mommydndn.app.ui.component.SearchUnderHeader
import com.mommydndn.app.ui.component.Searchbar
import com.mommydndn.app.ui.component.modal.CheckListModal
import com.mommydndn.app.ui.theme.GreyOpacity400
import com.mommydndn.app.ui.viewmodel.SignUpViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TownCheckScreen(
    navHostController: NavHostController,
    viewModel: SignUpViewModel,
    fusedLocationClient: FusedLocationProviderClient
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

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
            searchNearTowns(fusedLocationClient, viewModel)
        } else {
        }
        Log.d("TownCheckScreen", "권한이 거부되었습니다.")
    }

    LaunchedEffect(Unit) {
        checkAndRequestPermissions(
            context,
            permissions,
            launcher,
            onPermissionGranted = {
                searchNearTowns(
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
                    keyword = keyword,
                    onValueChange = {
                        viewModel.updateKeyword(it)
                    },
                    clearAction = {
                        viewModel.updateKeyword("")
                    },
                    placeHolderText = "동명으로 검색해주세요 (ex. 서초동)",
                    backStackAction = { navHostController.popBackStack() },
                    searchAction = { }
                )
                SearchUnderHeader(
                    headerText = if (keyword == "") "근처동네를 찾아왔어요." else "\'" + keyword + "\'" + " 검색결과",
                    searchAction = {
                        checkAndRequestPermissions(
                            context,
                            permissions,
                            launcher,
                            onPermissionGranted = {
                                searchNearTowns(
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
                pagingItems = if (searchType == NearestSearchType.KEYWORD) pagingItemsByKeyword else pagingItemsByLocation,
                onItemClick = { emdItem ->
                    scope.launch {
                        focusManager.clearFocus()
                        sheetState.show()
                    }
                    viewModel.updateEmdId(emdItem?.id)
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
            CheckListModal(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
                closeAction = {
                    scope.launch { sheetState.hide() }
                },

                completeAction = { viewModel.signUp(signUpInfo) },
                contentList = terms,
                titleCheckBoxText = "[필수] 통합 이용약관 동의"
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

private fun searchNearTowns(
    fusedLocationClient: FusedLocationProviderClient,
    viewModel: SignUpViewModel
) {
    try {
        fusedLocationClient.lastLocation.addOnSuccessListener {
            viewModel.updateLocation(LocationInfo(latitude = it.latitude, longitude = it.longitude))
        }
    } catch (e: SecurityException) {
        Log.d("TownCheckScreen", e.stackTraceToString())
    }
}

private fun checkAndRequestPermissions(
    context: Context,
    permissions: Array<String>,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
    onPermissionGranted: () -> Unit
) {
    if (permissions.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }) {
        onPermissionGranted()
        Log.d("TownCheckScreen", "권한이 이미 존재합니다.")
    } else {
        launcher.launch(permissions)
    }
}



