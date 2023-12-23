package com.mommydndn.app.ui.features.care.jobopening.post.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun Negotiable(
    checked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick,
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(
                id = if (checked) {
                    R.drawable.icon_checked_mark
                } else {
                    R.drawable.icon_not_checked_mark
                }
            ),
            contentDescription = "Negotiable_check_mark",
            modifier = Modifier.size(32.dp),
            tint = if (checked) Salmon600 else Grey200
        )

        Text(
            text = stringResource(R.string.negotiable),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.paragraph300.copy(
                fontWeight = FontWeight.Normal,
                color = Grey600
            )
        )
    }
}

@Preview
@Composable
private fun PreviewNegotiable_NotChecked() {
    Negotiable(
        checked = false,
        onClick = {},
        modifier = Modifier
            .background(White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
    )
}

@Preview
@Composable
private fun PreviewNegotiable_Checked() {
    Negotiable(
        checked = true,
        onClick = {},
        modifier = Modifier
            .background(White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
    )
}