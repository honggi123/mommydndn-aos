package com.mommydndn.app.ui.features.care.agency.register

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.components.common.MommyDnDnTopAppBar
import com.mommydndn.app.ui.features.care.component.GetPhotosField
import com.mommydndn.app.ui.theme.Grey300

@Composable
internal fun RegisterCareAgencyScreen(
    onCloseClick: () -> Unit,
    uris: List<Uri>,
    onPhotosAdded: (List<Uri>) -> Unit,
    onRemoveClick: (Uri) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        MommyDnDnTopAppBar(
            leading = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_close),
                    contentDescription = "MommyDnDnTopAppBar_Close",
                    modifier = Modifier.size(size = 36.dp),
                    tint = Grey300,
                )
            },
            onLeadingClick = onCloseClick,
            title = stringResource(R.string.register_care_agency_profile),
            modifier = Modifier,
        )

        LazyColumn {
            item {
                CoverPhoto(
                    uris = uris,
                    onPhotosAdded = onPhotosAdded,
                    onRemoveClick = onRemoveClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {

            }
        }
    }
}

@Composable
private fun CoverPhoto(
    uris: List<Uri>,
    onPhotosAdded: (List<Uri>) -> Unit,
    onRemoveClick: (Uri) -> Unit,
    modifier: Modifier = Modifier,
) {
    GetPhotosField(
        title = stringResource(R.string.cover_photo),
        subtitle = stringResource(id = R.string.required),
        uris = uris,
        onPhotosAdded = onPhotosAdded,
        onRemoveClick = onRemoveClick,
        modifier = modifier,
        maxSize = 5,
    )
}

@Composable
private fun ProfilePhoto(
    uri: Uri,
    onPhotoChange: (Uri) -> Unit,
    onRemoveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    
}