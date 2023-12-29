package com.mommydndn.app.ui.components.inputfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun SelectField(
    modifier: Modifier = Modifier,
    label: String = "",
    value: String = "",
    onClickSelection: () -> Unit,
    isSelected: Boolean = false
) {
    val textColor = if (isSelected) Grey800 else Grey400

    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey500
                )
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 56.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Grey50)
                .padding(
                    start = 20.dp,
                    end = 8.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Normal,
                    color = textColor
                )
            )
            Image(
                painter = painterResource(id = R.drawable.icon_arrow_down),
                contentDescription = "Icon/arrow-down-line",
                modifier = Modifier
                    .size(size = 28.dp)
                    .clickable { onClickSelection() }
            )
        }
    }
}

@Preview
@Composable
private fun SelectFieldPreview() {
    SelectField(Modifier, label = "label", value = "value", isSelected = true, onClickSelection = {})
}