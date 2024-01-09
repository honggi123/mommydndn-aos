package com.mommydndn.app.ui.care.components.post

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.utils.extensions.checkImagesPermission

@Composable
fun GetPhotoPostSection(
    uri: Uri?,
    onPhotoChange: (Uri?) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.profile_photo),
    subtitle: String = stringResource(id = R.string.required),
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

    PostSection(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .width(108.dp) // TODO
                .height(96.dp)
                .background(
                    color = Grey50,
                    shape = RoundedCornerShape(6.dp),
                )
                .clickable {
                    if (checkImagesPermission(context)) {
                        getContent.launch("image/*")
                    } else {
                        requestPermissions.launch(permissions)
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