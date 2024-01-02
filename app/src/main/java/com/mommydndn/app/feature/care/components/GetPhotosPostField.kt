package com.mommydndn.app.feature.care.components

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import coil.compose.rememberImagePainter
import com.mommydndn.app.BuildConfig
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.util.extensions.checkImagesPermission
import kotlin.math.max

@Composable
internal fun GetPhotosPostField(
    uris: List<Uri>,
    onPhotosChange: (List<Uri>) -> Unit,
    onRemoveClick: (Uri) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.photo),
    subtitle: String = stringResource(id = R.string.optional),
    maxSize: Int = 10,
) {
    val getMultipleContents = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { selectedUris ->
        onPhotosChange(selectedUris.take(maxSize))
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
                getMultipleContents.launch("image/*")
            }
        }

    val context = LocalContext.current
    
    PostField(
        title = title,
        subtitle = subtitle,
        modifier = modifier,
    ) {
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
                            .padding(top = 4.dp, end = 4.dp),
                        maxSize = maxSize,
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

@Composable
internal fun AddPhotoCell(
    onClick: () -> Unit,
    size: Int,
    modifier: Modifier = Modifier,
    maxSize: Int = 10,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(shape = RoundedCornerShape(6.dp))
            .background(color = Grey50)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_camera),
            contentDescription = "PostCareJobOpeningAddPhotoCell_Camera",
            tint = Grey300
        )

        Text(
            text = "$size/$maxSize",
            style = MaterialTheme.typography.paragraph300.merge(
                color = Grey500,
                fontWeight = FontWeight.Normal,
            )
        )
    }
}

@Composable
internal fun PhotoCell(
    uri: Uri,
    onRemoveClick: (Uri) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = rememberImagePainter(uri),
            contentDescription = "PhotoCell_UriPhoto",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, end = 4.dp)
                .clip(shape = RoundedCornerShape(6.dp))
                .run {
                    if (BuildConfig.DEBUG) {
                        background(color = Grey50) // preview
                    } else {
                        this
                    }
                },
            contentScale = ContentScale.Crop,
        )

        Icon(
            painter = painterResource(id = R.drawable.icon_circle_x),
            contentDescription = "PhotoCell_CircleX",
            modifier = Modifier
                .size(24.dp)
                .align(alignment = Alignment.TopEnd)
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null, // todo
                ) {
                    onRemoveClick(uri)
                },
            tint = Color.Unspecified,
        )
    }
}

@Preview
@Composable
private fun GetPhotosPostField_EmptyUris() {
    GetPhotosPostField(
        title = stringResource(R.string.photo),
        subtitle = stringResource(id = R.string.optional),
        uris = emptyList(),
        onPhotosChange = {},
        onRemoveClick = {},
        modifier = Modifier
            .background(White)
            .padding(horizontal = 24.dp),
    )
}

@Preview
@Composable
private fun GetPhotosPostField_WithUris() {
    val uris = buildList<Uri> {
        repeat(6) { add(Uri.EMPTY) }
    }

    GetPhotosPostField(
        title = stringResource(R.string.photo),
        subtitle = stringResource(id = R.string.optional),
        uris = uris,
        onPhotosChange = {},
        onRemoveClick = {},
        modifier = Modifier
            .background(White)
            .padding(horizontal = 24.dp),
    )
}