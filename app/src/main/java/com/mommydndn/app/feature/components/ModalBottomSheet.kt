package com.mommydndn.app.feature.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph400

@Composable
internal fun ModalBottomSheet(
    dismissText: String,
    onDismissClick: () -> Unit,
    actionText: String,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier,
    dismissTextColor: Color = Salmon600,
    dismissBackgroundColor: Color = Salmon200,
    actionTextColor: Color = White,
    actionBackgroundColor: Color = Salmon600,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .background(
                color = White,
                shape = RoundedCornerShape(24.dp),
            ),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp, bottom = 24.dp),
        ) {
            ModalBottomSheetHandleBar(modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(20.dp))

            this@Column.content()

            Spacer(modifier = Modifier.height(28.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp),) {
                ModalBottomSheetButton(
                    onClick = onDismissClick,
                    text = dismissText,
                    textColor = dismissTextColor,
                    backgroundColor = dismissBackgroundColor,
                    modifier = Modifier.weight(1F),
                )

                ModalBottomSheetButton(
                    onClick = onActionClick,
                    text = actionText,
                    textColor = actionTextColor,
                    backgroundColor = actionBackgroundColor,
                    modifier = Modifier.weight(1F),
                )
            }
        }
    }
}

@Composable
internal fun ModalBottomSheetHandleBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(64.dp)
            .height(6.dp)
            .background(
                color = Grey200,
                shape = CircleShape,
            )
    )
}

@Composable
internal fun ModalBottomSheetButton(
    onClick: () -> Unit,
    text: String,
    textColor: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
        ),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.paragraph400.merge(
                fontWeight = FontWeight.Medium,
            ),
        )
    }
}