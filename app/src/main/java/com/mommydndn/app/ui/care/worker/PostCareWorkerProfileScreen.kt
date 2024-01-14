package com.mommydndn.app.ui.care.worker

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.OtherOption
import com.mommydndn.app.domain.model.user.NeighborhoodDistance
import com.mommydndn.app.ui.care.components.post.AuthenticationPostSection
import com.mommydndn.app.ui.care.components.post.BioPostSection
import com.mommydndn.app.ui.care.components.post.CareTypesPostSection
import com.mommydndn.app.ui.care.components.post.GetPhotoPostSection
import com.mommydndn.app.ui.care.components.post.NeighborhoodPostSection
import com.mommydndn.app.ui.care.components.post.NeighborhoodUiModel
import com.mommydndn.app.ui.care.components.post.OtherOptionsPostSection
import com.mommydndn.app.ui.care.components.post.PostNextButton
import com.mommydndn.app.ui.care.components.post.PostTopAppBar
import com.mommydndn.app.ui.care.components.post.TopAppBarHeight
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey50

@Composable
internal fun PostCareWorkerProfileScreen(
    onCloseClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PostCareWorkerProfileViewModel = hiltViewModel()
) {
    val photoUri by viewModel.photoUri.collectAsStateWithLifecycle()
    val bio by viewModel.bio.collectAsStateWithLifecycle()
    val neighborhood by viewModel.neighborhood.collectAsStateWithLifecycle()
    val careTypes by viewModel.careTypes.collectAsStateWithLifecycle()
    val authentications by viewModel.authentications.collectAsStateWithLifecycle()
    val options by viewModel.options.collectAsStateWithLifecycle()

    val nearbyNeighborhoodsCount = with(neighborhood) {
        when (distance) {
            NeighborhoodDistance.IMMEDIATE -> nearbyNeighborhoods.immediateNeighborhoods.size
            NeighborhoodDistance.NEARBY -> nearbyNeighborhoods.nearbyNeighborhoods.size
            NeighborhoodDistance.DISTANT -> nearbyNeighborhoods.distantNeighborhoods.size
            NeighborhoodDistance.VERY_DISTANT -> nearbyNeighborhoods.veryDistantNeighborhoods.size
        }
    }

    PostCareWorkerProfileContent(
        onCloseClick = onCloseClick,
        photoUri = photoUri?.let(Uri::parse),
        onPhotoChange = { uri ->
            viewModel.setPhotoUris(uri?.toString())
        },
        bio = bio,
        onBioChange = viewModel::setBio,
        neighborhood = NeighborhoodUiModel(
            name = neighborhood.name,
            address = neighborhood.address,
            nearbyNeighborhoodsCount = nearbyNeighborhoodsCount,
        ),
        onNeighborhoodClick = {},
        onNearbyNeighborhoodsClick = {},
        careTypes = careTypes,
        onCareTypeClick = { careType ->
            careTypes.toMutableList()
                .apply {
                    if (contains(careType)) {
                        remove(careType)
                    } else {
                        add(careType)
                    }
                }
                .let(viewModel::setCareTypes)
        },
        authentications = authentications,
        onAuthenticateClick = {},
        options = options,
        onOptionClick = { option ->
            options.toMutableList()
                .apply {
                    if (contains(option)) {
                        remove(option)
                    } else {
                        add(option)
                    }
                }
                .let(viewModel::setOptions)
        },
        onNextClick = onNextClick,
        modifier = modifier
    )
}

@Composable
internal fun PostCareWorkerProfileContent(
    onCloseClick: () -> Unit,
    photoUri: Uri?,
    onPhotoChange: (Uri?) -> Unit,
    bio: String,
    onBioChange: (String) -> Unit,
    neighborhood: NeighborhoodUiModel,
    onNeighborhoodClick: () -> Unit,
    onNearbyNeighborhoodsClick: () -> Unit,
    careTypes: List<CareType>,
    onCareTypeClick: (CareType) -> Unit,
    authentications: List<String>,
    onAuthenticateClick: () -> Unit,
    options: List<OtherOption>,
    onOptionClick: (OtherOption) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        PostTopAppBar(
            leading = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_close),
                    contentDescription = "PostTopAppBar_Close",
                    modifier = Modifier.size(size = 36.dp),
                    tint = Grey300,
                )
            },
            onLeadingClick = onCloseClick,
            title = stringResource(id = R.string.register_care_provider),
            modifier = Modifier
                .fillMaxWidth()
                .height(TopAppBarHeight),
        )

        LazyColumn(
            modifier = Modifier
                .background(Grey50)
                .weight(1F),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            item {
                GetPhotoPostSection(
                    uri = photoUri,
                    onPhotoChange = onPhotoChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    title = stringResource(R.string.profile_photo),
                    subtitle = stringResource(id = R.string.required),
                )
            }

            item {
                BioPostSection(
                    bio = bio,
                    onBioChange = onBioChange,
                    modifier = Modifier,
                )
            }

            item {
                NeighborhoodPostSection(
                    neighborhood = neighborhood,
                    onNeighborhoodClick = onNeighborhoodClick,
                    onNearbyNeighborhoodsClick = onNearbyNeighborhoodsClick,
                    modifier = Modifier,
                )
            }

            item {
                CareTypesPostSection(
                    selectedCareTypes = careTypes,
                    onCareTypeClick = onCareTypeClick,
                    modifier = Modifier,
                )
            }

            item {
                AuthenticationPostSection(
                    authentications = authentications,
                    onAuthenticateClick = onAuthenticateClick,
                    modifier = Modifier,
                )
            }

            item {
                OtherOptionsPostSection(
                    selectedOptions = options,
                    onClick = onOptionClick,
                    modifier = Modifier,
                    options = listOf(
                        OtherOption.Pets,
                        OtherOption.CCTV,
                        OtherOption.Occupancy,
                        OtherOption.NonSmoker,
                        OtherOption.NoReligion,
                    )
                )
            }
        }

        PostNextButton(onClick = onNextClick, modifier = Modifier)
    }
}