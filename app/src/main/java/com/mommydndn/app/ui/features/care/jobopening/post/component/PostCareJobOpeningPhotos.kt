package com.mommydndn.app.ui.features.care.jobopening.post.component

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.mommydndn.app.R
import com.mommydndn.app.ui.features.care.component.AddPhotoCell
import com.mommydndn.app.ui.features.care.component.PhotoCell
import com.mommydndn.app.ui.features.care.provider.register.component.checkImagesPermission
import com.mommydndn.app.ui.features.care.provider.register.component.kFieldPaddingValues
import com.mommydndn.app.ui.theme.White
import kotlin.math.max

@Composable
internal fun PostCareJobOpeningPhotos(
    uris: List<Uri>,
    onPhotosAdded: (List<Uri>) -> Unit,
    onRemoveClick: (Uri) -> Unit,
    modifier: Modifier = Modifier,
) {
    val getMultipleContents = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = onPhotosAdded,
    )

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
                getMultipleContents.launch("image/*")
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
            title = stringResource(R.string.photo),
            subtitle = stringResource(R.string.optional),
            modifier = Modifier.fillMaxWidth(),
        )

        BoxWithConstraints(modifier = Modifier) {
            val size = maxWidth / 3 - (4 * 2).dp

            val row = (uris.size / 3 + 1)

            val height = row * size + max(row - 1, 0) * 4.dp

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                userScrollEnabled = false,
            ) {
                item {
                    AddPhotoCell(
                        onClick = {
                            if (checkImagesPermission(context)) {
                                getMultipleContents.launch("image/*")
                            } else {
                                requestPermissions.launch(permissions)
                            }
                        },
                        size = uris.size,
                        modifier = Modifier
                            .aspectRatio(1F)
                            .padding(top = 4.dp, end = 4.dp), // space for (x)
                    )
                }

                items(uris) { uri ->
                    PhotoCell(
                        uri = uri,
                        onRemoveClick = onRemoveClick,
                        modifier = Modifier.aspectRatio(1F),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddPhotos_EmptyUri() {
    PostCareJobOpeningPhotos(
        uris = emptyList(),
        onPhotosAdded = {},
        onRemoveClick = {},
        modifier = Modifier
            .background(White)
            .padding(horizontal = 24.dp),
    )
}

@Preview
@Composable
private fun AddPhotos_WithUris() {
    val uris = buildList<Uri> {
        repeat(6) { add(Uri.EMPTY) }
    }
    PostCareJobOpeningPhotos(
        uris = uris,
        onPhotosAdded = {},
        onRemoveClick = {},
        modifier = Modifier
            .background(White)
            .padding(horizontal = 24.dp),
    )
}