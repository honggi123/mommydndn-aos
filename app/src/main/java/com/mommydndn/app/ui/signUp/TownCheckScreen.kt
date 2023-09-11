package com.mommydndn.app.ui.signUp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mommydndn.app.ui.component.RadioListBox
import com.mommydndn.app.ui.component.SearchUnderHeader
import com.mommydndn.app.ui.component.Searchbar
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.viewmodel.SignUpViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TownCheckScreen(
    navHostController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val (textState, setTextState) = remember { mutableStateOf("") }

    val context = LocalContext.current

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
        } else {
            Log.d("TownCheckScreen", "권한이 거부되었습니다.")
        }
    }

    Scaffold(
        topBar = {
            Column {
                Searchbar(
                    keyword = textState,
                    onValueChange = setTextState,
                    clearAction = { setTextState("") },
                    placeHolderText = "동명으로 검색해주세요 (ex. 서초동)",
                    backStackAction = { navHostController.popBackStack() }
                )
                SearchUnderHeader(headerText = "근처 동네", searchAction = {
                    checkAndRequestPermissions(
                        context,
                        permissions,
                        launcher
                    )
                })
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            RadioListBox(items = listOf("1", "2", "3"))
        }
    }
}

private fun checkAndRequestPermissions(
    context: Context,
    permissions: Array<String>,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
) {
    if (permissions.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }) {
        Log.d("TownCheckScreen", "권한이 이미 존재합니다.")
    } else {
        launcher.launch(permissions)
    }
}

@Preview
@Composable
fun previewTownCheckScreen() {
    MommydndnaosTheme {
        val navController = rememberNavController()
        TownCheckScreen(navController)
    }
}
