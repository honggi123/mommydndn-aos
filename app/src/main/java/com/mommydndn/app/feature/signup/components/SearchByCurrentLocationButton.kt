package com.mommydndn.app.feature.signup.components

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.location.Coordinates
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun SearchByCurrentLocationButton(
    onResult: (Coordinates) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val requestPermissions = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
    ) { result ->
        @SuppressLint("MissingPermission")
        if (result.values.any()) {
            with(fusedLocationClient) {
                getCurrentLocation(
                    LocationRequest.PRIORITY_HIGH_ACCURACY,
                    CancellationTokenSource().token
                ).addOnSuccessListener { location ->
                    with(location) {
                        onResult(Coordinates(latitude, longitude))
                    }
                }.addOnFailureListener { exception ->
                    // todo
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Salmon200, RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    requestPermissions.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    )
                },
            ),
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_current_location),
                contentDescription = "SearchByCurrentLocationButton_CurrentLocation",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified,
            )

            Text(
                text = stringResource(id = R.string.search_by_current_location),
                color = Salmon600,
                style = MaterialTheme.typography.paragraph300.merge(
                    fontWeight = FontWeight.Medium,
                )
            )
        }
    }
}

@Preview
@Composable
private fun SearchByCurrentLocationButton_Preview() {
    SearchByCurrentLocationButton(
        onResult = {},
        modifier = Modifier,
    )
}