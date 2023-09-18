package com.mommydndn.app.ui.signUp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Task
import com.mommydndn.app.data.model.EmdItem
import com.mommydndn.app.data.model.displayName
import com.mommydndn.app.ui.component.RadioListBox
import com.mommydndn.app.ui.component.SearchUnderHeader
import com.mommydndn.app.ui.component.Searchbar
import com.mommydndn.app.ui.component.modal.CheckListModal
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.viewmodel.SignUpViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TownCheckScreen(
    navHostController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel(),
    fusedLocationClient: FusedLocationProviderClient
) {
    val context = LocalContext.current

    val isModalVisible by viewModel.isModalVisible.collectAsState()
    val terms by viewModel.terms.collectAsState()
    val nearTowns by viewModel.nearTowns.collectAsState()
    val keyword by viewModel.keyword.collectAsState()

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

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            Column {
                Searchbar(
                    keyword = keyword,
                    onValueChange = {
                        Log.e("keyword", it)
                        viewModel.updateKeyword(it)
                        viewModel.searchByKeyword(it)
                    },
                    clearAction = {
                        viewModel.updateKeyword("")
                        viewModel.searchByKeyword("")
                    },
                    placeHolderText = "동명으로 검색해주세요 (ex. 서초동)",
                    backStackAction = { navHostController.popBackStack() },
                    searchAction = { }
                )
                SearchUnderHeader(
                    headerText = if (keyword == "") "근처동네" else "\'" + keyword + "\'" + " 검색결과",
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
                items = nearTowns,
                onItemClick = { viewModel.showModal() },
                itemNameDisplay = EmdItem::displayName
            )
        }
    }

    AnimatedVisibility(
        visible = isModalVisible,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight },
            animationSpec = spring(stiffness = 100f, dampingRatio = Spring.DampingRatioLowBouncy)
        )
    ) {
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = { viewModel.hideModal() }
        ) {
            val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
            dialogWindowProvider.window.setGravity(Gravity.BOTTOM)

            CheckListModal(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
                closeAction = { viewModel.hideModal() },
                completeAction = { },
                contentList = terms
            )
        }
    }

}

private fun searchNearTowns(
    fusedLocationClient: FusedLocationProviderClient,
    viewModel: SignUpViewModel
) {
    try {
        fusedLocationClient.lastLocation.addOnSuccessListener {
            viewModel.searchByLoaction(it.latitude, it.longitude)
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



