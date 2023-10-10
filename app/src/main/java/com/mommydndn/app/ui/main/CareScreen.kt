package com.mommydndn.app.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mommydndn.app.MainBottomNavigationBar
import com.mommydndn.app.R
import com.mommydndn.app.ui.MainNav
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CareScreen(
    navHostController: NavHostController,
    viewModel: MainViewModel
) {
    Scaffold(topBar = {}, bottomBar = {
        MainBottomNavigationBar(
            navController = navHostController,
            currentRoute = MainNav.Care.route
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .size(72.dp),
            backgroundColor = Salmon600
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pencil),
                contentDescription = null,
                tint = White
            )
        }
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        )
    }


}