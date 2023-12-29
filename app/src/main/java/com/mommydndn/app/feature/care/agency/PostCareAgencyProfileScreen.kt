package com.mommydndn.app.feature.care.agency

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.OtherOption
import com.mommydndn.app.domain.model.user.NearbyNeighborhoods
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.domain.model.user.NeighborhoodDistance
import com.mommydndn.app.feature.care.components.BioPostField
import com.mommydndn.app.feature.care.components.CareTypesPostField
import com.mommydndn.app.feature.care.components.GetPhotoPostField
import com.mommydndn.app.feature.care.components.GetPhotosPostField
import com.mommydndn.app.feature.care.components.NeighborhoodPostField
import com.mommydndn.app.feature.care.components.OtherOptionsPostField
import com.mommydndn.app.feature.care.components.PostTopAppBar
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey50
import java.util.Collections

@Composable
internal fun RegisterCareAgencyScreen(
    onCloseClick: () -> Unit,
    coverPhotoUris: List<Uri>,
    onCoverPhotosAdded: (List<Uri>) -> Unit,
    onRemoveCoverPhotoClick: (Uri) -> Unit,
    profilePhotoUri: Uri?,
    onProfilePhotoChange: (Uri?) -> Unit,
    bio: String,
    onBioChange: (String) -> Unit,
    neighborhood: Neighborhood,
    onNeighborhoodClick: () -> Unit,
    onNearbyNeighborhoodsClick: () -> Unit,
    careTypes: List<CareType>,
    onCareTypeClick: (CareType) -> Unit,
    options: List<OtherOption>,
    onOptionClick: (OtherOption) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        PostTopAppBar(
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

        LazyColumn(
            modifier = Modifier.background(Grey50),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            item {
                GetPhotosPostField(
                    title = stringResource(R.string.cover_photo),
                    subtitle = stringResource(id = R.string.required),
                    uris = coverPhotoUris,
                    onPhotosChange = onCoverPhotosAdded,
                    onRemoveClick = onRemoveCoverPhotoClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    maxSize = 5,
                )
            }

            item {
                GetPhotoPostField(
                    uri = profilePhotoUri,
                    onPhotoChange = onProfilePhotoChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {
                BioPostField(
                    bio = bio,
                    onBioChange = onBioChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {
                NeighborhoodPostField(
                    neighborhood = neighborhood,
                    onNeighborhoodClick = onNeighborhoodClick,
                    onNearbyNeighborhoodsClick = onNearbyNeighborhoodsClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {
                CareTypesPostField(
                    selectedCareTypes = careTypes,
                    onClick = onCareTypeClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {
                OtherOptionsPostField(
                    options = Collections.singletonList(OtherOption.AS),
                    selectedOptions = options,
                    onClick = onOptionClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }
        }
    }
}

@Preview
@Composable
private fun PostCareAgencyProfile_Preview() {
    RegisterCareAgencyScreen(
        onCloseClick = {},
        coverPhotoUris = emptyList(),
        onCoverPhotosAdded = {},
        onRemoveCoverPhotoClick = {},
        profilePhotoUri = null,
        onProfilePhotoChange = {},
        bio = "",
        onBioChange = {},
        neighborhood = Neighborhood(
            id = 0,
            name = "",
            address = "",
            latitude = 0.0,
            longitude = 0.0,
            distance = NeighborhoodDistance.IMMEDIATE,
            nearbyNeighborhoods = NearbyNeighborhoods(
                immediateNeighborhoods = emptyList(),
                nearbyNeighborhoods = emptyList(),
                distantNeighborhoods = emptyList(),
                veryDistantNeighborhoods = emptyList()
            )
        ),
        onNeighborhoodClick = {},
        onNearbyNeighborhoodsClick = {},
        careTypes = emptyList(),
        onCareTypeClick = {},
        options = emptyList(),
        onOptionClick = {},
        modifier = Modifier,
    )
}