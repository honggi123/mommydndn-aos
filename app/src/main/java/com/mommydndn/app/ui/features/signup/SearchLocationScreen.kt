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
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.mommydndn.app.ui.features.signup.component.TosCheckListModal
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.GreyOpacity400
import com.mommydndn.app.ui.theme.Shapes
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.util.PermissionUtils
import kotlinx.coroutines.launch
import java.security.Permission

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

    var currentPagingFlow by rememberSaveable { mutableStateOf(viewModel.nearbyLocations) }
    var searchType by rememberSaveable { mutableStateOf<SearchType>(SearchType.MY_LOCATION) }

    LaunchedEffect(searchType) {
        currentPagingFlow = when (searchType) {
            SearchType.KEYWORD -> viewModel.searchedLocations
            SearchType.MY_LOCATION -> viewModel.nearbyLocations
        }
    }

    val pagingItems = currentPagingFlow.collectAsLazyPagingItems()

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
            val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
            if (areGranted) {
                Log.d("LocationSearchRoute", "권한이 동의되었습니다.")
                searchNearByLocations(
                    fusedLocationClient = fusedLocationClient,
                    searchAction = { latitude, longitude ->
                        viewModel.searchNearby(
                            coordinatesInfo = CoordinatesInfo(
                                latitude = latitude,
                                longitude = longitude
                            )
                        )
                        searchType = SearchType.MY_LOCATION
                    }
                )
            } else {
                Log.d("LocationSearchRoute", "권한이 거부되었습니다.")
            }
        }

    LaunchedEffect(Unit) {
        requestPermissions(
            context = context,
            launcher = launcher,
            permissions = viewModel.permissions,
            onResult = {
                searchNearByLocations(
                    fusedLocationClient = fusedLocationClient,
                    searchAction = { latitude, longitude ->
                        viewModel.searchNearby(
                            coordinatesInfo = CoordinatesInfo(latitude = latitude, longitude = longitude)
                        )
                    }
                )
            }
        )
    }

    val uiState by viewModel.locationSearchUiState.collectAsStateWithLifecycle()

    when (val uiState = uiState) {
        is SignUpUiState.LocationSearch.Loading -> {
            // TODO
        }

        is SignUpUiState.LocationSearch.Success -> {
            LocationSearchScreen(
                uiState = uiState,
                modifier = Modifier.fillMaxSize(),
                pagingItems = pagingItems,
                onBackButtonClick = { onBackButtonClick() },
                onSearchClick = {
                    requestPermissions(
                        context = context,
                        launcher = launcher,
                        permissions = viewModel.permissions,
                        onResult = {
                            searchNearByLocations(
                                fusedLocationClient = fusedLocationClient,
                                searchAction = { latitude, longitude ->
                                    viewModel.searchNearby(
                                        coordinatesInfo = CoordinatesInfo(latitude = latitude, longitude = longitude)
                                    )
                                }
                            )
                        }
                    )
                    searchType = SearchType.MY_LOCATION
                },
                onItemClick = { item ->
                    scope.launch { sheetState.show() }
                    viewModel.updateMyLocation(item)
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
                onSearchTypeChange = { searchType = it },
                onClearClick = { viewModel.clearKeyword() },
                onKeywordChange = {
                    viewModel.search(it)
                    searchType = SearchType.KEYWORD
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

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LocationSearchScreen(
    uiState: SignUpUiState.LocationSearch.Success,
    onSearchClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    onClearClick: () -> Unit,
    onSearchTypeChange: (SearchType) -> Unit,
    onKeywordChange: (String) -> Unit,
    onItemClick: (LocationInfo) -> Unit,
    pagingItems: LazyPagingItems<LocationInfo>,
    scaffoldState: ScaffoldState,
    sheetState: ModalBottomSheetState,
    itemList: List<TermsOfService>,
    onDialogDismiss: () -> Unit,
    onDialogComplete: (List<TermsOfService>) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            LocationSearchTopAppBar(
                onBackButtonClick = onBackButtonClick,
                onSearchClick = onSearchClick,
                onClearClick = onClearClick,
                onSearchTypeChange = onSearchTypeChange,
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
        onDialogDismiss = onDialogDismiss,
        onDialogComplete = onDialogComplete,
    )
}

@Composable
fun LocationSearchTopAppBar(
    onBackButtonClick: () -> Unit,
    onSearchClick: () -> Unit,
    onClearClick: () -> Unit,
    onSearchTypeChange: (SearchType) -> Unit,
    onKeywordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var keyword by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        Searchbar(
            modifier = Modifier.fillMaxWidth(),
            keyword = keyword,
            onValueChange = {
                onKeywordChange(it)
                keyword = it
                onSearchTypeChange(SearchType.KEYWORD)
            },
            clearAction = { onClearClick() },
            placeHolderText = stringResource(R.string.searched_neighborhood),
            backStackAction = { onBackButtonClick() },
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

private fun requestPermissions(
    permissions: Array<String>,
    context: Context,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
    onResult: () -> Unit,
) {
    PermissionUtils.checkAndRequestPermissions(
        context,
        permissions,
        launcher,
        onPermissionGranted = { onResult() }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetModal(
    sheetState: ModalBottomSheetState,
    itemList: List<TermsOfService>,
    onDialogDismiss: () -> Unit,
    onDialogComplete: (List<TermsOfService>) -> Unit,
) {

    val (isAllChecked, setIsAllChecked) = remember { mutableStateOf(false) }

    var checkedStates by remember { mutableStateOf(List(itemList.size) { false }) }

    if (checkedStates.size != itemList.size) {
        checkedStates = List(itemList.size) { false }
    }

    val requiredCheckList = itemList.filter { it.isRequired }

    val isNextButtonEnabled by remember(checkedStates) {
        mutableStateOf(requiredCheckList.all { item ->
            val itemIndex = itemList.indexOf(item)
            checkedStates.getOrNull(itemIndex) == true
        })
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContentColor = Color.Transparent,
        sheetBackgroundColor = Color.Transparent,
        scrimColor = GreyOpacity400,
        sheetElevation = 0.dp,
        sheetContent = {
            TosCheckListModal(
                onDismiss = { onDialogDismiss() },
                onComplete = { onDialogComplete(it) },
                itemList = itemList,
                checkBoxTitle = stringResource(R.string.total_terms_agreement),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
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

@Composable
fun LocationsRadioListBox(
    uiState: SignUpUiState.LocationSearch.Success,
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
    onItemClick: (LocationInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    var checkedStates by rememberSaveable { mutableStateOf(List(pagingItems.itemCount) { false }) }

    if (checkedStates.size != pagingItems.itemCount) {
        checkedStates = List(pagingItems.itemCount) { false }
    }

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
                    checked = checkedStates[index],
                    onCheckedChange = { isChecked ->
                        checkedStates = checkedStates.toMutableList().apply {
                            this[index] = isChecked
                        }

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


