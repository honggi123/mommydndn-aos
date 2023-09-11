package com.mommydndn.app.ui.signUp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mommydndn.app.ui.component.ListBox
import com.mommydndn.app.ui.component.SearchUnderHeader
import com.mommydndn.app.ui.component.Searchbar
import com.mommydndn.app.ui.theme.MommydndnaosTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TownCheckScreen(
    navHostController: NavHostController
) {
    val (textState, setTextState) = remember { mutableStateOf("") }

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
                SearchUnderHeader(headerText = "근처 동네")
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            ListBox(items = listOf("1", "2", "3"))
        }
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
