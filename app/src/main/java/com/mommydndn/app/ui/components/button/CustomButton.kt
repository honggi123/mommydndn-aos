package com.mommydndn.app.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.common.ButtonColor
import com.mommydndn.app.data.model.common.ButtonColorType
import com.mommydndn.app.data.model.common.ButtonSizeType
import com.mommydndn.app.data.model.common.MinMaxRange
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.Salmon200
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400

@Composable
fun CustomButton(
    text: String = "",
    color: ButtonColor,
    colorType: ButtonColorType,
    sizeType: ButtonSizeType,
    rangeType: MinMaxRange,
    onClick: () -> Unit = {}
) {
    val backgroundColor = when (color) {
        ButtonColor.WHITE -> White
        ButtonColor.SALMON -> if (colorType == ButtonColorType.FILLED) Salmon600 else Salmon200
        ButtonColor.GREY -> Grey100
    }

    val textColor = when (color) {
        ButtonColor.WHITE -> Grey500
        ButtonColor.SALMON -> if (colorType == ButtonColorType.FILLED) White else Salmon600
        ButtonColor.GREY -> Grey600
    }

    val paddingValues = when (sizeType) {
        ButtonSizeType.LARGE -> PaddingValues(vertical = 16.dp)
        ButtonSizeType.MEDIUM -> PaddingValues(12.dp)
        ButtonSizeType.SMALL -> PaddingValues(8.dp)
    }

    val textStyle = when (sizeType) {
        ButtonSizeType.LARGE -> MaterialTheme.typography.paragraph400.copy(
            fontWeight = FontWeight.Medium,
            color = textColor
        )

        ButtonSizeType.MEDIUM, ButtonSizeType.SMALL -> MaterialTheme.typography.paragraph300.copy(
            fontWeight = FontWeight.Medium,
            color = textColor
        )
    }

    val modifier = if (rangeType == MinMaxRange.MAX) {
        Modifier.fillMaxWidth()
    } else {
        Modifier.then(
            if (sizeType == ButtonSizeType.LARGE) Modifier.width(153.dp)
            else Modifier
        )
    }

    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier,
        contentPadding = paddingValues
    ) {
        Text(
            text = text,
            style = textStyle,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    MommydndnaosTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomButton(
                text = "text",
                color = ButtonColor.SALMON,
                colorType = ButtonColorType.FILLED,
                sizeType = ButtonSizeType.LARGE,
                rangeType = MinMaxRange.MIN
            )
        }
    }

}
