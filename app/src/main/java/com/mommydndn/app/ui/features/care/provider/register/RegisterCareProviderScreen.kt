package com.mommydndn.app.ui.features.care.provider.register

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.OtherCondition
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.ui.components.button.NextButton
import com.mommydndn.app.ui.components.common.TopAppBarHeight
import com.mommydndn.app.ui.features.care.provider.register.component.RegisterCareProviderAuthentication
import com.mommydndn.app.ui.features.care.provider.register.component.RegisterCareProviderBio
import com.mommydndn.app.ui.features.care.provider.register.component.RegisterCareProviderCareTypes
import com.mommydndn.app.ui.features.care.provider.register.component.RegisterCareProviderNeighborhood
import com.mommydndn.app.ui.features.care.provider.register.component.RegisterCareProviderOtherConditions
import com.mommydndn.app.ui.features.care.provider.register.component.RegisterCareProviderProfilePhoto
import com.mommydndn.app.ui.features.care.provider.register.component.RegisterCareProviderTopAppBar
import com.mommydndn.app.ui.theme.Grey50

@Composable
internal fun RegisterCareProviderRoute(
    onCloseClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterCareProviderViewModel = hiltViewModel()
) {
    val photoUri by viewModel.photoUri.collectAsStateWithLifecycle()
    val bio by viewModel.bio.collectAsStateWithLifecycle()
    val neighborhood by viewModel.neighborhood.collectAsStateWithLifecycle()
    val careTypes by viewModel.careTypes.collectAsStateWithLifecycle()
    val authentications by viewModel.authentications.collectAsStateWithLifecycle()
    val conditions by viewModel.otherConditions.collectAsStateWithLifecycle()

    RegisterCareProviderScreen(
        onCloseClick = onCloseClick,
        photoUri = photoUri.takeIf { !it.isNullOrEmpty() }?.let(Uri::parse),
        onPhotoChange = { uri ->
            viewModel.setPhotoUris(uri?.toString())
        },
        bio = bio,
        onBioChange = {
            viewModel.setBio(it)
        },
        neighborhood = neighborhood,
        onNeighborhoodClick = {},
        onNearbyNeighborhoodsClick = {},
        careTypes = careTypes,
        onCareTypeClick = { careType ->
            careTypes.toMutableList().apply {
                if (contains(careType)) {
                    remove(careType)
                } else {
                    add(careType)
                }
            }.let {
                viewModel.setCareTypes(it)
            }
        },
        authentications = authentications,
        onAuthenticateClick = {},
        conditions = conditions,
        onConditionClick = { condition ->
            conditions.toMutableList().apply {
                if (contains(condition)) {
                    remove(condition)
                } else {
                    add(condition)
                }
            }.let {
                viewModel.setOtherConditions(it)
            }
        },
        onNextClick = onNextClick,
        modifier = modifier,
    )
}

@Composable
internal fun RegisterCareProviderScreen(
    onCloseClick: () -> Unit,
    photoUri: Uri?,
    onPhotoChange: (Uri?) -> Unit,
    bio: String,
    onBioChange: (String) -> Unit,
    neighborhood: Neighborhood,
    onNeighborhoodClick: () -> Unit,
    onNearbyNeighborhoodsClick: () -> Unit,
    careTypes: List<CareType>,
    onCareTypeClick: (CareType) -> Unit,
    authentications: List<String>,
    onAuthenticateClick: () -> Unit,
    conditions: List<OtherCondition>,
    onConditionClick: (OtherCondition) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        RegisterCareProviderTopAppBar(
            onCloseClick = onCloseClick,
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
                RegisterCareProviderProfilePhoto(
                    uri = photoUri,
                    onPhotoChange = onPhotoChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {
                RegisterCareProviderBio(
                    bio = bio,
                    onBioChange = onBioChange,
                    modifier = Modifier,
                )
            }

            item {
                RegisterCareProviderNeighborhood(
                    neighborhood = neighborhood,
                    onNeighborhoodClick = onNeighborhoodClick,
                    onNearbyNeighborhoodsClick = onNearbyNeighborhoodsClick,
                    modifier = Modifier,
                )
            }

            item {
                RegisterCareProviderCareTypes(
                    selectedCareTypes = careTypes,
                    onClick = onCareTypeClick,
                    modifier = Modifier,
                )
            }

            item {
                RegisterCareProviderAuthentication(
                    authentications = authentications,
                    onAuthenticateClick = onAuthenticateClick,
                    modifier = Modifier,
                )
            }

            item {
                RegisterCareProviderOtherConditions(
                    selectedConditions = conditions,
                    onClick = onConditionClick,
                    modifier = Modifier,
                )
            }
        }

        NextButton(onClick = onNextClick, modifier = Modifier)
    }
}

@Preview
@Composable
private fun RegisterCareProviderRoute_Preview() {
    RegisterCareProviderRoute(
        onCloseClick = {},
        onNextClick = {},
        modifier = Modifier.fillMaxSize(),
        viewModel = RegisterCareProviderViewModel(),
    )
}