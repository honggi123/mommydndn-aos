package com.mommydndn.app.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mommydndn.app.R
import com.mommydndn.app.ui.models.ImageInputFieldType
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.GreyOpacity400
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun ImageInputField(
    modifier: Modifier = Modifier,
    inputType: ImageInputFieldType,
) {
    when (inputType) {
        is ImageInputFieldType.Add -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .width(width = 108.dp)
                    .height(height = 96.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = Grey50)
                    .clickable { inputType.onClick?.let { it() } }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = "",
                    tint = Grey300
                )
                Text(
                    text = inputType.index.toString() + "/10",
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Normal,
                        color = Grey500
                    )
                )
            }
        }

        is ImageInputFieldType.Editable -> {
            Box(
                modifier = modifier
                    .width(width = 108.dp)
                    .height(height = 96.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = White)
            ) {
                Image(
                    painter = rememberImagePainter(inputType.imageUri),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(6.dp)),
                    contentScale = ContentScale.Crop
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_x_circle),
                    contentDescription = "",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 98.dp,
                            y = (-5).dp
                        )
                )
            }
        }

        is ImageInputFieldType.Ineditable -> {
            Box(
                modifier = modifier
                    .width(width = 108.dp)
                    .height(height = 96.dp)
                    .clip(shape = RoundedCornerShape(6.dp))
                    .background(color = White)
            ) {
                Image(
                    painter = rememberImagePainter(inputType.imageUri),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(6.dp))
                )
            }
        }
    }


}

