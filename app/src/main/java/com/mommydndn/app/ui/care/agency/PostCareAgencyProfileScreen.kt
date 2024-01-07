package com.mommydndn.app.ui.care.agency

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
import com.mommydndn.app.feature.care.components.post.PostTopAppBar
import com.mommydndn.app.feature.care.components.post.BioPostSection
import com.mommydndn.app.feature.care.components.post.CareTypesPostSection
import com.mommydndn.app.feature.care.components.post.GetPhotoPostSection
import com.mommydndn.app.feature.care.components.post.GetPhotosPostSection
import com.mommydndn.app.feature.care.components.post.NeighborhoodPostSection
import com.mommydndn.app.feature.care.components.post.NeighborhoodUiModel
import com.mommydndn.app.feature.care.components.post.OtherOptionsPostSection
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
    neighborhood: NeighborhoodUiModel,
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
                GetPhotosPostSection(
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
                GetPhotoPostSection(
                    uri = profilePhotoUri,
                    onPhotoChange = onProfilePhotoChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {
                BioPostSection(
                    bio = bio,
                    onBioChange = onBioChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {
                NeighborhoodPostSection(
                    neighborhood = neighborhood,
                    onNeighborhoodClick = onNeighborhoodClick,
                    onNearbyNeighborhoodsClick = onNearbyNeighborhoodsClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {
                CareTypesPostSection(
                    selectedCareTypes = careTypes,
                    onCareTypeClick = onCareTypeClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {
                OtherOptionsPostSection(
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
private fun PostCareAgencyProfilePreview() {
    RegisterCareAgencyScreen(
        onCloseClick = {},
        coverPhotoUris = emptyList(),
        onCoverPhotosAdded = {},
        onRemoveCoverPhotoClick = {},
        profilePhotoUri = null,
        onProfilePhotoChange = {},
        bio = "",
        onBioChange = {},
        neighborhood = NeighborhoodUiModel(
            name = "서초동",
            address = "서울 서초구 서초중앙로 15",
            nearbyNeighborhoodsCount = 24,
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