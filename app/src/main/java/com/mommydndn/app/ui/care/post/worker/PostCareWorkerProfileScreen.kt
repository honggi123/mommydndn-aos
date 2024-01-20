package com.mommydndn.app.ui.care.post.worker

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.CareWorkerOtherCondition
import com.mommydndn.app.ui.care.post.components.BioPostSection
import com.mommydndn.app.ui.care.post.components.CareTypesPostSection
import com.mommydndn.app.ui.care.post.components.GetPhotoPostSection
import com.mommydndn.app.ui.care.post.components.NeighborhoodPostSection
import com.mommydndn.app.ui.care.post.components.NeighborhoodUiModel
import com.mommydndn.app.ui.care.post.components.OtherConditionsPostSection
import com.mommydndn.app.ui.care.post.components.PostNextButton
import com.mommydndn.app.ui.care.post.components.PostTopAppBar
import com.mommydndn.app.ui.care.post.components.TopAppBarHeight
import com.mommydndn.app.ui.care.post.components.VerificationPostSection
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey50

@Composable
internal fun PostCareWorkerProfileScreen(
    onCloseClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PostCareWorkerProfileViewModel = hiltViewModel()
) {
    /*
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
     */
}

data class PostCareWorkerProfileUiModel(
    val profileImageUri: Uri?,
    val bio: String,
    val neighborhood: NeighborhoodUiModel,
    val careTypes: List<CareType>,
    val verifications: List<String>,
    val otherConditions: List<CareWorkerOtherCondition>,
)

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
    otherConditions: List<CareWorkerOtherCondition>,
    onOtherConditionClick: (CareWorkerOtherCondition) -> Unit,
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
                VerificationPostSection(
                    verifications = authentications,
                    onAuthenticateClick = onAuthenticateClick,
                    modifier = Modifier,
                )
            }

            item {
                OtherConditionsPostSection(
                    otherConditions = CareWorkerOtherCondition.entries,
                    selectedConditions = otherConditions,
                    onClick = {},
                    modifier = Modifier,
                )
            }
        }

        PostNextButton(onClick = onNextClick, modifier = Modifier)
    }
}