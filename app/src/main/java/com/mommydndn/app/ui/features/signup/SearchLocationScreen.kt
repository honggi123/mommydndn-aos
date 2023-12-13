package com.mommydndn.app.ui.features.signup

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.location.CoordinatesInfo
import com.mommydndn.app.domain.model.location.LocationInfo
import com.mommydndn.app.domain.model.location.SearchType
import com.mommydndn.app.domain.model.tos.TermsOfService
import com.mommydndn.app.ui.components.box.SearchUnderHeader
import com.mommydndn.app.ui.components.inputfield.RadioListItem
import com.mommydndn.app.ui.components.inputfield.Searchbar
import com.mommydndn.app.ui.features.signup.component.TermsCheckListModal
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.GreyOpacity400
import com.mommydndn.app.ui.theme.Shapes
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.util.PermissionUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun LocationSearchRoute(
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

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            Log.d("TownCheckScreen", "권한이 동의되었습니다.")
            searchNearByLocations(
                fusedLocationClient,
                searchAction = { latitude, longitude ->
                    viewModel.updateLocationInfo(
                        CoordinatesInfo(
                            latitude = latitude,
                            longitude = longitude
                        )
                    )
                }
            )
        } else {
            Log.d("TownCheckScreen", "권한이 거부되었습니다.")
        }
    }

    LaunchedEffect(Unit) {
        searchNearbyLocationsWithPermission(
            context = context,
            launcher = launcher,
            fusedLocationClient = fusedLocationClient,
            viewModel = viewModel
        )
    }

    val uiState by viewModel.locationSearchUiState.collectAsStateWithLifecycle()

    var currentPagingFlow by remember { mutableStateOf(viewModel.nearestLocations) }

    LaunchedEffect(uiState.searchType) {
        currentPagingFlow = when (uiState.searchType) {
            SearchType.KEYWORD -> viewModel.searchedLocations
            SearchType.MY_LOCATION -> viewModel.nearestLocations
        }
    }

    val pagingItems = currentPagingFlow.collectAsLazyPagingItems()

    when (val uiState = uiState) {
        is SignUpUiState.LocationSearch.Loading -> {
            // TODO
        }

        is SignUpUiState.LocationSearch.LoadSuccess -> {
            LocationSearchScreen(
                uiState = uiState,
                modifier = Modifier.fillMaxSize(),
                keyword = uiState.keyword,
                pagingItems = pagingItems,
                navigateToPreviousScreen = {
                    onBackButtonClick()
                },
                onSearchClick = {
                    searchNearbyLocationsWithPermission(
                        context,
                        launcher,
                        fusedLocationClient,
                        viewModel
                    )
                },
                onItemClick = { item ->
                    scope.launch { sheetState.show() }
                    viewModel.updateLocationId(item?.id)
                },
                onDialogDismiss = { scope.launch { sheetState.hide() } },
                onDialogItemSelected = { index, isChecked ->
                    viewModel.updateTermsApprovalStatus(
                        id = uiState.TOSList[index].id,
                        isChecked = isChecked
                    )
                },
                itemList = uiState.TOSList,
                onDialogComplete = { viewModel.signUp(uiState.TOSList) },
                scaffoldState = scaffoldState,
                sheetState = sheetState,
                onClearClick = { viewModel.clearKeyword() },
                onKeywordChange = { viewModel.updateKeyword(it) }
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

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LocationSearchScreen(
    uiState: SignUpUiState.LocationSearch.LoadSuccess,
    keyword: String,
    onSearchClick: () -> Unit,
    navigateToPreviousScreen: () -> Unit,
    onClearClick: () -> Unit,
    onKeywordChange: (String) -> Unit,
    onItemClick: (LocationInfo) -> Unit,
    pagingItems: LazyPagingItems<LocationInfo>,
    scaffoldState: ScaffoldState,
    sheetState: ModalBottomSheetState,
    itemList: List<TermsOfService>,
    onDialogItemSelected: (Int, Boolean) -> Unit,
    onDialogDismiss: () -> Unit,
    onDialogComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            LocationSearchTopAppBar(
                navigateToPreviousScreen = { navigateToPreviousScreen() },
                onSearchClick = { onSearchClick() },
                keyword = keyword,
                onClearClick = { onClearClick() },
                onKeywordChange = { onKeywordChange(it) },
                modifier = Modifier.fillMaxWidth()
            )
        },
        scaffoldState = scaffoldState,
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            LocationsRadioListBox(
                uiState = uiState,
                pagingItems = pagingItems,
                onItemClick = { item ->
                    if (item != null) {
                        onItemClick(item)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    BottomSheetModal(
        sheetState = sheetState,
        itemList = itemList,
        onDialogItemSelected = onDialogItemSelected,
        onDialogDismiss = onDialogDismiss,
        onDialogComplete = onDialogComplete,
    )
}

@Composable
fun LocationSearchTopAppBar(
    navigateToPreviousScreen: () -> Unit,
    onSearchClick: () -> Unit,
    onClearClick: () -> Unit,
    onKeywordChange: (String) -> Unit,
    keyword: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Searchbar(
            modifier = Modifier.fillMaxWidth(),
            keyword = keyword,
            onValueChange = { onKeywordChange(it) },
            clearAction = { onClearClick() },
            placeHolderText = stringResource(R.string.searched_neighborhood),
            backStackAction = { navigateToPreviousScreen() },
        )
        SearchUnderHeader(
            modifier = Modifier.fillMaxWidth(),
            headerText = if (keyword.isBlank()) {
                stringResource(R.string.search_location_keyword)
            } else {
                stringResource(R.string.result_searched_neighborhood, keyword)
            },
            searchAction = { onSearchClick() }
        )
    }
}

private fun searchNearbyLocationsWithPermission(
    context: Context,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
    fusedLocationClient: FusedLocationProviderClient,
    viewModel: SignUpViewModel
) {
    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    PermissionUtils.checkAndRequestPermissions(
        context,
        permissions,
        launcher,
        onPermissionGranted = {
            searchNearByLocations(
                fusedLocationClient,
                searchAction = { latitude, longitude ->
                    viewModel.updateLocationInfo(
                        CoordinatesInfo(
                            latitude = latitude,
                            longitude = longitude
                        )
                    )
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetModal(
    sheetState: ModalBottomSheetState,
    itemList: List<TermsOfService>,
    onDialogItemSelected: (Int, Boolean) -> Unit,
    onDialogDismiss: () -> Unit,
    onDialogComplete: () -> Unit,
) {
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContentColor = Color.Transparent,
        sheetBackgroundColor = Color.Transparent,
        scrimColor = GreyOpacity400,
        sheetElevation = 0.dp,
        sheetContent = {
            TermsCheckListModal(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
                onDismiss = { onDialogDismiss() },
                onItemSelected = { index, isChecked -> onDialogItemSelected(index, isChecked) },
                onComplete = { onDialogComplete() },
                itemList = itemList,
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

private fun searchNearByLocations(
    fusedLocationClient: FusedLocationProviderClient,
    searchAction: (Double, Double) -> Unit
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

@Composable
fun LocationsRadioListBox(
    uiState: SignUpUiState.LocationSearch.LoadSuccess,
    pagingItems: LazyPagingItems<LocationInfo>,
    onItemClick: (LocationInfo) -> Unit,
    modifier: Modifier = Modifier
) {

    if (pagingItems.itemCount == 0) {
        EmptyResultRadioListBox(modifier = modifier)
    } else {
        HasResultRadioListBox(
            pagingItems = pagingItems,
            onItemClick = onItemClick,
            selectedItemId = uiState.selectedLocationId,
            modifier = Modifier
        )
    }
}

@Composable
fun EmptyResultRadioListBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(White),
    ) {
        Text(
            modifier = Modifier.padding(start = 97.5.dp, top = 96.dp),
            text = stringResource(id = R.string.no_result_and_check_keyword),
            style = MaterialTheme.typography.paragraph300.copy(
                fontWeight = FontWeight.Normal,
                color = Grey400,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun HasResultRadioListBox(
    pagingItems: LazyPagingItems<LocationInfo>,
    selectedItemId: Int,
    onItemClick: (LocationInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(top = 6.dp)
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        itemsIndexed(pagingItems) { index, item ->
            key(item?.id ?: index) {
                RadioListItem(
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            top = 16.dp,
                            end = 12.dp,
                            bottom = 16.dp
                        )
                        .clip(Shapes.large),
                    checked = selectedItemId == item?.id,
                    onCheckedChange = { isChecked ->
                        if (isChecked && item != null) {
                            onItemClick(item)
                        }
                    },
                    text = item?.fullName ?: ""
                )
            }
        }
    }
}


