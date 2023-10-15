package com.mommydndn.app.ui.feature.care.joboffer.write

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Chip
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mommydndn.app.R
import com.mommydndn.app.data.model.CaringType
import com.mommydndn.app.data.model.CaringTypeItem
import com.mommydndn.app.data.model.SalaryType
import com.mommydndn.app.data.model.SalaryTypeItem
import com.mommydndn.app.data.model.WorkHoursType
import com.mommydndn.app.data.model.WorkHoursTypeItem
import com.mommydndn.app.ui.components.box.PostTextFieldBox
import com.mommydndn.app.ui.components.box.SelectScopeBox
import com.mommydndn.app.ui.components.box.SubtextBox
import com.mommydndn.app.ui.components.box.SubtextBoxSize
import com.mommydndn.app.ui.components.button.CtaButton
import com.mommydndn.app.ui.components.button.SelectButton
import com.mommydndn.app.ui.components.chip.ClickableChip
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.common.ImageInputField
import com.mommydndn.app.ui.components.common.SelectField
import com.mommydndn.app.ui.components.common.TextInpuField
import com.mommydndn.app.ui.models.ImageInputFieldType
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400

@Composable
fun JobOfferWriteScreen() {

    val careTypes = listOf(
        CaringTypeItem(CaringType.HOUSEKEEPING),
        CaringTypeItem(CaringType.NURSING),
        CaringTypeItem(CaringType.PARENTING),
        CaringTypeItem(CaringType.SCHOOL)
    )

    val workHoursTypes = listOf(
        WorkHoursTypeItem(WorkHoursType.SHORT),
        WorkHoursTypeItem(WorkHoursType.REGULAR)
    )
    val salaryTypes = listOf(
        SalaryTypeItem(SalaryType.HOURLY),
        SalaryTypeItem(SalaryType.DAILY),
        SalaryTypeItem(SalaryType.WEEKLY),
        SalaryTypeItem(SalaryType.MONTHLY),
        SalaryTypeItem(SalaryType.NEGOTIATION)
    )


    Column(
        modifier = Modifier
            .background(White)
    ) {
        Header(leftContent = {
            Image(
                painter = painterResource(id = R.drawable.ic_x),
                contentDescription = "",
                modifier = Modifier
                    .size(size = 36.dp)
            )
        }, centerContent = {
            Text(
                text = "구인글 쓰기",
                style = MaterialTheme.typography.paragraph400.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey700,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
        }, rightContent = {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(White),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp),
            ) {
                Text(
                    text = "불러오기",
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Medium,
                        color = Grey500,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
        })

        LazyColumn {
            item {
                PostTextFieldBox(title = "", content = "")
            }
            item {
                SubtextBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    titleText = "필요한 돌봄",
                    subtitleText = "(필수)",
                    size = SubtextBoxSize.S
                )
                Box(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        top = 16.dp,
                        bottom = 40.dp,
                        end = 24.dp
                    )
                ) {
                    Row {
                        careTypes.forEach { type ->
                            ClickableChip(
                                modifier = Modifier.height(36.dp),
                                text = type.caringType.value,
                                selected = type.isSelected,
                                onClick = {}
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Grey50)
                        .padding(20.dp)
                )
            }
            item {
                SubtextBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    titleText = "일하는 시간",
                    subtitleText = "(필수)",
                    size = SubtextBoxSize.S
                )
                Column(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        top = 16.dp,
                        bottom = 40.dp,
                        end = 24.dp
                    )
                ) {
                    Row {
                        workHoursTypes.forEach { type ->
                            ClickableChip(
                                modifier = Modifier.height(36.dp),
                                text = type.workHoursType.value,
                                selected = type.isSelected,
                                onClick = {}
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    )
                    SelectScopeBox(
                        modifier = Modifier.fillMaxWidth(),
                        label = "날짜",
                        option1Text = "오는날짜",
                        option2Text = "내일날짜",
                        onOption1Clicked = { /*TODO*/ },
                        onOption2Clicked = { /*TODO*/ },
                        isChecked = false,
                        onCheckedChange = {}
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    )
                    SelectScopeBox(
                        modifier = Modifier.fillMaxWidth(),
                        label = "시간",
                        option1Text = "시작시간",
                        option2Text = "종료시간",
                        onOption1Clicked = { /*TODO*/ },
                        onOption2Clicked = { /*TODO*/ },
                        isChecked = false,
                        onCheckedChange = {},
                        checkListText = "협의 가능해요"
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Grey50)
                        .padding(20.dp)
                )
            }
            item {
                SubtextBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    titleText = "일하는 장소",
                    subtitleText = "(필수)",
                    size = SubtextBoxSize.S
                )
                Box(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        top = 16.dp,
                        bottom = 40.dp,
                        end = 24.dp
                    )
                ) {
                    SelectField(
                        modifier = Modifier.fillMaxWidth(),
                        label = "주소",
                        value = "서초구",
                        onSelection = {}
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Grey50)
                        .padding(20.dp)
                )
            }
            item {
                SubtextBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    titleText = "임금",
                    subtitleText = "(필수)",
                    size = SubtextBoxSize.S
                )
                Column(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        top = 16.dp,
                        bottom = 40.dp,
                        end = 24.dp
                    )
                ) {
                    Row {
                        salaryTypes.forEach { type ->
                            ClickableChip(
                                modifier = Modifier.height(36.dp),
                                text = type.salaryType.value,
                                selected = type.isSelected,
                                onClick = {}
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    TextInpuField(
                        label = "시급",
                        placeHolderText = "10,000",
                        descriptionText = "2023년 최저시급은 9,620원이에요"
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Grey50)
                        .padding(20.dp)
                )
            }
            item {
                SubtextBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    titleText = "사진",
                    subtitleText = "(선택)",
                    size = SubtextBoxSize.S
                )
                Box(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        top = 16.dp,
                        bottom = 40.dp,
                        end = 24.dp
                    )
                ) {
                    ImageInputField(inputType = ImageInputFieldType.Add(index = 0))
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Grey50)
                        .padding(20.dp)
                )
            }
            item {
                SubtextBox(
                    modifier = Modifier
                        .fillMaxWidth(),
                    titleText = "기타조건",
                    subtitleText = "(선택)",
                    size = SubtextBoxSize.S
                )
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 24.dp,
                            top = 16.dp,
                            bottom = 40.dp,
                            end = 24.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    CtaButton(modifier = Modifier.width(342.dp), text = "다음으로") {}
                }
            }
        }
    }
}

@Preview
@Composable
fun previewJobOfferWriteScreen() {
    MommydndnaosTheme {
        JobOfferWriteScreen()
    }
}


