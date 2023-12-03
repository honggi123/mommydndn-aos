package com.mommydndn.app.ui.features.care.modal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey200
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph400
import com.mommydndn.app.ui.theme.shadow700

// todo: preview composables
@Composable
internal fun CareFilterModalBottomSheet(
    onCloseClick: () -> Unit,
    onUpdateClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .then(shadow700)
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

            ModalBottomSheetButtons(
                onCloseClick = onCloseClick,
                onUpdateClick = onUpdateClick,
                modifier = Modifier.fillMaxWidth(),
            )
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
                shape = RoundedCornerShape(size = 50.dp),
            )
    )
}

@Composable
internal fun ModalBottomSheetTitleWithRefreshButton(
    title: String,
    onRefreshClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier,
            style = MaterialTheme.typography.paragraph400.merge(
                color = Grey700,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
            )
        )

        Row(
            modifier = Modifier.clickable(onClick = onRefreshClick),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_rewind),
                contentDescription = "TitleWithRefreshButton_Rewind",
                modifier = Modifier.size(24.dp),
            )

            Text(
                text = stringResource(R.string.reset),
                modifier = Modifier,
                style = MaterialTheme.typography.caption200.merge(
                    color = Grey600,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                )
            )
        }
    }
}

@Composable
internal fun ModalBottomSheetHorizontalDivider(modifier: Modifier = Modifier) {
    Divider(
        color = Grey50,
        thickness = 1.5.dp,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    )
}

@Composable
internal fun ModalBottomSheetButtons(
    onCloseClick: () -> Unit,
    onUpdateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Button(
            modifier = Modifier.weight(1F),
            elevation = ButtonDefaults.elevation(0.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Salmon200),
            onClick = onCloseClick,
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.close),
                style = MaterialTheme.typography.paragraph400.merge(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = Salmon600,
                ),
            )
        }

        Button(
            modifier = Modifier.weight(1F),
            elevation = ButtonDefaults.elevation(0.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Salmon600),
            onClick = onUpdateClick,
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.apply),
                style = MaterialTheme.typography.paragraph400.merge(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = White,
                ),
            )
        }
    }
}

@Composable
internal fun ModalBottomSheetSelectableButton(
    selected: Boolean,
    selectedBorderColor: Color,
    selectedBackgroundColor: Color,
    unselectedBorderColor: Color,
    unselectedBackgroundColor: Color,
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val backgroundColor = if (selected) {
        selectedBackgroundColor
    } else {
        unselectedBackgroundColor
    }

    val borderColor = if (selected) {
        selectedBorderColor
    } else {
        unselectedBorderColor
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = backgroundColor)
            .border(
                border = BorderStroke(1.dp, borderColor),
                shape = CircleShape,
            ).clickable {
                onClick(!selected)
            },
        content = content,
    )
}