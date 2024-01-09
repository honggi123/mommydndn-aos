package com.mommydndn.app.deprecated.signup.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.MommydndnTheme
import com.mommydndn.app.ui.theme.Shapes
import com.mommydndn.app.ui.theme.paragraph500
import com.mommydndn.app.ui.theme.Shadow500

@Composable
fun SquareButton(
    imageResourceId: Int,
    text: String,
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    Crossfade(modifier = modifier, targetState = isSelected, label = "") { isSelected ->
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .then(if (isSelected) Shadow500 else modifier)
                .background(
                    color = if (isSelected) Grey100 else Grey50,
                    shape = Shapes.large
                )
                .clickable(onClick = { onClick(!isSelected) })
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = imageResourceId),
                    contentDescription = "",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                        .padding(0.9.dp)
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.paragraph500.copy(
                        fontWeight = FontWeight.Bold,
                        color = Grey600
                    )
                )
            }
        }
    }
}


@Preview
@Composable
fun previewSquareButton() {
    MommydndnTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(368.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SquareButton(
                    modifier = Modifier.weight(1f),
                    imageResourceId = R.drawable.icon_building,
                    text = stringResource(R.string.agency_user),
                    onClick = { }
                )
                SquareButton(
                    modifier = Modifier.weight(1f),
                    imageResourceId = R.drawable.icon_person,
                    text = stringResource(R.string.individual_user),
                    onClick = { }
                )
            }
        }
    }
}