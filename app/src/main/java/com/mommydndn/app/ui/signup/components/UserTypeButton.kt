package com.mommydndn.app.ui.signup.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Shadow500
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph500

@Composable
internal fun UserTypeButton(
    onClick: () -> Unit,
    @DrawableRes iconDrawableId: Int,
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.then(
            if (selected) Shadow500 else Modifier
        ),
        color = Color.Transparent,
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (!selected) {
                        Grey50
                    } else {
                        Grey100
                    },
                    shape = RoundedCornerShape(12.dp),
                )
                .aspectRatio(1F)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClick,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painter = painterResource(id = iconDrawableId),
                    contentDescription = "UserTypeButton_Icon",
                    modifier = Modifier.size(72.dp),
                    tint = Color.Unspecified,
                )

                Text(
                    text = text,
                    color = Grey600,
                    style = MaterialTheme.typography.paragraph500.merge(
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
private fun UserTypeButtonPreview() {
    Row(
        modifier = Modifier
            .background(White)
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        UserTypeButton(
            onClick = {},
            iconDrawableId = R.drawable.icon_building,
            text = stringResource(id = R.string.agency_user),
            selected = false,
            modifier = Modifier.weight(1F),
        )

        UserTypeButton(
            onClick = {},
            iconDrawableId = R.drawable.icon_person,
            text = stringResource(id = R.string.individual_user),
            selected = false,
            modifier = Modifier.weight(1F),
        )
    }
}