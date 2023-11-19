package com.mommydndn.app.ui.components.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.data.model.care.CaringType
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.MommydndnTypography
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100

@Composable
fun SittingCategory(
    modifier: Modifier = Modifier,
    caringType: CaringType
) {

    val imgResourceId = when (caringType) {
        CaringType.PARENTING -> R.drawable.ic_baby_bottle
        CaringType.NURSING -> R.drawable.ic_bathchair
        CaringType.SCHOOL -> R.drawable.ic_bus
        CaringType.HOUSEKEEPING -> R.drawable.ic_housekeeping
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredWidth(width = 110.dp)
            .requiredHeight(height = 94.dp)
            .clip(shape = RoundedCornerShape(6.dp))
            .background(color = White)
            .border(
                border = BorderStroke(1.dp, Color(0xffffe7cc)),
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Image(
            painter = painterResource(id = imgResourceId),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color(0xffffa544)),
            modifier = Modifier
                .requiredSize(size = 32.dp)
        )
        Text(
            text = caringType.value,
            color = Grey700,
            textAlign = TextAlign.Center,
            style = MommydndnTypography.caption100.copy(
                color = Grey700,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }
}