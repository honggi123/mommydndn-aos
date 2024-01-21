package com.mommydndn.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.mommydndn.app.ui.home.Home
import com.mommydndn.app.ui.home.components.nearbyJobOpenings
import com.mommydndn.app.ui.home.components.nearestCareProviders
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.MommydndnTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MommydndnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    Home(
                        onInquiryClick = { /*TODO*/ },
                        onNotificationClick = { /*TODO*/ },
                        banners = emptyList(),
                        nearestCareProviders = nearestCareProviders,
                        onAllCareProvidersClick = { /*TODO*/ },
                        nearbyJobOpenings = nearbyJobOpenings,
                        onMoreJobOpeningsClick = { /*TODO*/ },
                        onReviewClick = { /*TODO*/ },
                        modifier = Modifier.background(Grey50),
                    )
                }
            }
        }
    }
}
