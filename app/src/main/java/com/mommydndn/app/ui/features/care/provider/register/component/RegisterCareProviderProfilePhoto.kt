package com.mommydndn.app.ui.features.care.provider.register.component

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.mommydndn.app.R
import com.mommydndn.app.ui.features.care.component.PhotoCell
import com.mommydndn.app.ui.features.care.jobopening.post.component.PostFieldTitle
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.White

// todo
val kFieldPaddingValues = PaddingValues(start = 24.dp, top = 28.dp, end = 24.dp, bottom = 40.dp)

@Composable
internal fun RegisterCareProviderProfilePhoto(
    uri: Uri?,
    onPhotoChange: (Uri?) -> Unit,
    modifier: Modifier = Modifier,
) {
    val getContent =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { photoUri ->
            if (photoUri != null) {
                onPhotoChange(photoUri)
            }
        }

    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        arrayOf(
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
        )
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
    } else {
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    val requestPermissions =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { result ->
            if (result.all { it.value }) {
                getContent.launch("image/*")
            }
        }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .background(White)
            .padding(kFieldPaddingValues),
        verticalArrangement = Arrangement.spacedBy(28.dp),
    ) {
        PostFieldTitle(
            title = stringResource(R.string.profile_photo),
            subtitle = stringResource(id = R.string.required),
            modifier = Modifier.wrapContentSize(),
        )

        Box(
            modifier = Modifier
                .width(108.dp)
                .height(96.dp)
                .run {
                    if (uri == null) {
                        background(
                            color = Grey50,
                            shape = RoundedCornerShape(6.dp),
                        ).clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                if (checkImagesPermission(context)) {
                                    getContent.launch("image/*")
                                } else {
                                    requestPermissions.launch(permissions)
                                }
                            },
                        )
                    } else {
                        this
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            if (uri == null) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_camera),
                    contentDescription = "RegisterCareProviderProfilePhoto_Camera",
                    modifier = Modifier.size(24.dp),
                    tint = Grey300,
                )
            } else {
                PhotoCell(
                    uri = uri,
                    onRemoveClick = {
                        onPhotoChange(null)
                    },
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

internal fun checkImagesPermission(context: Context): Boolean {
    return if (
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        true
    } else if (
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE &&
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        true
    } else {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }
}

@Preview
@Composable
private fun ProfilePhoto_Null() {
    RegisterCareProviderProfilePhoto(
        uri = null,
        onPhotoChange = {},
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun ProfilePhoto_WithUri() {
    RegisterCareProviderProfilePhoto(
        uri = Uri.EMPTY,
        onPhotoChange = {},
        modifier = Modifier.fillMaxWidth()
    )
}