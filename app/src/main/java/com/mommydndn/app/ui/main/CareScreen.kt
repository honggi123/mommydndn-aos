package com.mommydndn.app.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mommydndn.app.MainBottomNavigationBar
import com.mommydndn.app.R
import com.mommydndn.app.data.model.TabSize
import com.mommydndn.app.ui.MainNav
import com.mommydndn.app.ui.components.common.CustomTab
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.heading600
import com.mommydndn.app.ui.theme.heading800
import com.mommydndn.app.ui.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CareScreen(
    navHostController: NavHostController,
    viewModel: MainViewModel
) {
    Scaffold(topBar = {
        Header(leftContent = {
                Text(
                    text = "서초동",
                    style = MaterialTheme.typography.heading800.copy(
                        fontWeight = FontWeight.Bold,
                        color = Grey700,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "",
                    modifier = Modifier
                        .size(36.dp)
                )
        }, rightContent = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
            )

        }
        )
    }, bottomBar = {
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
        Column {
            CustomTab(
                size = TabSize.LARGE,
                onTabClick = {},
                tabs = listOf("구인글","시터님","안심업체")
            )
        }
    }


}