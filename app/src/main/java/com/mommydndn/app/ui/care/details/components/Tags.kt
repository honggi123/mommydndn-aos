package com.mommydndn.app.ui.care.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mommydndn.app.domain.model.CareAgencyOtherCondition
import com.mommydndn.app.domain.model.CareWorkerOtherCondition
import com.mommydndn.app.domain.model.OtherCondition
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.caption200

@Composable
internal fun DetailsOtherConditionTags(
    otherConditions: List<OtherCondition>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            otherConditions
                .map { it.displayName }
                .forEach { TagChip(tag = it) }
        }
    }
}

private val OtherCondition.displayName: String
    // TODO: 순서에 따라 접미어
    @Composable
    get() = when (this) {
        CareAgencyOtherCondition.AS -> "A/S 보장해요"
        CareWorkerOtherCondition.Pets -> "반려동물을 좋아하고"
        CareWorkerOtherCondition.CCTV -> "CCTV가 괜찮고"
        CareWorkerOtherCondition.Residential -> "입주가 가능하고"
        CareWorkerOtherCondition.NonSmoker -> "비흡연자이고"
        CareWorkerOtherCondition.NoReligion -> "무교이신 분"
    }

@Composable
private fun TagChip(tag: String) {
    Text(
        text = tag,
        modifier = Modifier
            .background(Grey50, CircleShape)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        color = Grey600,
        style = MaterialTheme.typography.caption200
    )
}