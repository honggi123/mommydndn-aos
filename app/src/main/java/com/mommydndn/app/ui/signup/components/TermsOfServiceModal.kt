package com.mommydndn.app.ui.signup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.TermsOfService
import com.mommydndn.app.ui.components.check.CheckListItem
import com.mommydndn.app.ui.components.check.CheckboxListItem
import com.mommydndn.app.ui.components.modal.MommydndnModal
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White

@Composable
internal fun TermsOfServiceModal(
    termsOfService: Map<TermsOfService, Boolean>,
    onCloseClick: () -> Unit,
    onNextClick: () -> Unit,
    onAgreeAllClick: () -> Unit,
    onTermsOfServiceClick: (TermsOfService) -> Unit,
    modifier: Modifier = Modifier,
) {
    val enabled = termsOfService.keys
        .filter { it.isRequired }
        .all { termsOfService.getValue(it) }

    MommydndnModal(
        dismissText = stringResource(id = R.string.close),
        onDismissClick = onCloseClick,
        actionText = stringResource(id = R.string.move_on),
        onActionClick = {
            if (enabled) {
                onNextClick()
            }
        },
        modifier = modifier,
        actionTextColor = if (enabled) {
            White
        } else {
            Grey600
        },
        actionBackgroundColor = if (enabled) {
            Salmon600
        } else {
            Grey100
        },
    ) {
        val checkedAll = termsOfService.values.all { it }

        CheckboxListItem(
            checked = checkedAll,
            onClick = {

            },
            text = stringResource(R.string.agree_all),
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            color = Grey50,
            thickness = 1.5.dp,
        )

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            termsOfService.forEach { entry ->
                val tos = entry.key

                val prefix = if (tos.isRequired) {
                    stringResource(id = R.string.required_square_brackets)
                } else {
                    stringResource(id = R.string.optional_square_brackets)
                }

                CheckListItem(
                    checked = entry.value,
                    text = "$prefix ${tos.title}",
                    onClick = {
                        onTermsOfServiceClick(tos)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }
        }
    }
}

@Preview
@Composable
private fun ToSModalBottomSheet_Preview() {
    var termsOfService by remember {
        mutableStateOf(
            listOf(
                "통합 이용약관 동의",
                "개인정보 처리방침 동의",
                "위치기반 서비스 약관 동의",
                "취소 및 환불규정 동의",
                "이벤트 및 광고 수신 동의",
            ).mapIndexed { index, title ->
                TermsOfService(
                    id = "$index",
                    title = title,
                    url = "",
                    isRequired = index != 4
                )
            }.associateWith { true }
        )
    }

    Box(
        modifier = Modifier
            .background(White)
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .background(Color(0x73454E5D))
                .fillMaxSize()
        )

        TermsOfServiceModal(
            termsOfService = termsOfService,
            onCloseClick = {},
            onNextClick = {},
            onAgreeAllClick = {},
            onTermsOfServiceClick = { tos ->
                termsOfService = termsOfService.toMutableMap().apply {
                    put(tos, !termsOfService.getValue(tos))
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 32.dp),
        )
    }
}