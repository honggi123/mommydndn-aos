package com.mommydndn.app.ui.features.care.joboffer.write

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mommydndn.app.R
import com.mommydndn.app.data.model.care.WorkHoursType
import com.mommydndn.app.ui.components.box.PostTextFieldBox
import com.mommydndn.app.ui.components.box.SelectButtonScopeBox
import com.mommydndn.app.ui.components.box.SelectScopeBox
import com.mommydndn.app.ui.components.box.SubtextBox
import com.mommydndn.app.ui.components.box.SubtextBoxSize
import com.mommydndn.app.ui.components.button.CtaButton
import com.mommydndn.app.ui.components.chip.ClickableChip
import com.mommydndn.app.ui.components.common.CheckBoxListItem
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.common.ImageInputField
import com.mommydndn.app.ui.components.common.SelectField
import com.mommydndn.app.ui.components.common.TextInpuField
import com.mommydndn.app.data.model.common.ImageInputFieldType
import com.mommydndn.app.data.model.common.SelectButtonContent
import com.mommydndn.app.ui.navigation.LocationSearchNav
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400
import com.mommydndn.app.utils.DateTimeUtils
import com.mommydndn.app.utils.NavigationUtils
import com.mommydndn.app.utils.NumberUtils
import com.mommydndn.app.utils.PermissionUtils
import java.util.Calendar

@Composable
fun JobOfferWriteScreen(
    navController: NavHostController,
    viewModel: JobOfferWriteViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val userInfo by viewModel.userInfo.collectAsState()

    val careTypes by viewModel.careTypes.collectAsState()
    val workHoursTypes by viewModel.workHoursTypes.collectAsState()
    val salaryTypes by viewModel.salaryTypes.collectAsState()
    val dayOfWeekTypes by viewModel.dayOfWeekTypes.collectAsState()

    val title by viewModel.title.collectAsState()
    val content by viewModel.content.collectAsState()

    val startDate by viewModel.stratDate.collectAsState()
    val endDate by viewModel.endDate.collectAsState()

    val startTime by viewModel.startTime.collectAsState()
    val endTime by viewModel.endTime.collectAsState()

    val salary by viewModel.salary.collectAsState()

    val photos by viewModel.photos.collectAsState()

    val etcCheckList by viewModel.etcCheckList.collectAsState()

    val startDatePicker = createDatePicker(
        calendar = calendar,
        context = context
    ) { year, month, dayOfMonth ->
        viewModel.setStartDate(year, month, dayOfMonth)
    }

    val endDatePicker = createDatePicker(
        calendar = calendar,
        context = context
    ) { year, month, dayOfMonth ->
        viewModel.setEndDate(year, month, dayOfMonth)
    }

    val startTimePicker = createTimePickerDialog(
        calendar = calendar,
        context = context
    ) { hour, min ->
        viewModel.setStartTime(hour, min)
    }

    val endTimePicker = createTimePickerDialog(
        calendar = calendar,
        context = context
    ) { hour, min ->
        viewModel.setEndTime(hour, min)
    }

    val takePhotoFromAlbumLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            viewModel.addSelectedPhotos(uriList)
        }

    startDatePicker.datePicker.minDate = calendar.timeInMillis
    endDatePicker.datePicker.minDate = calendar.timeInMillis

    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
    } else {
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            Log.d("JobOfferWriteScreen", "권한이 동의되었습니다.")
            takePhotoFromAlbumLauncher.launch("image/jpeg")
        } else {
        }
        Log.d("JobOfferWriteScreen", "권한이 거부되었습니다.")
    }


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
                    color = Grey700
                )
            )
        }, rightContent = {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .clickable { },
                text = "불러오기",
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey500
                )
            )
        })

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            PostTextFieldBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 6.dp),
                title = title,
                content = content,
                onTitleTextChanged = { viewModel.setTitle(it) },
                onContentTextChanged = { viewModel.setContent(it) }
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Grey50)
                    .padding(20.dp)
            )

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
                            onClick = { viewModel.selectCareTypes(type) }
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
                            onClick = { viewModel.selectWorkHoursType(type) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                )
                if (workHoursTypes.find { it.isSelected }?.workHoursType == WorkHoursType.REGULAR) {
                    SelectButtonScopeBox(
                        label = "요일",
                        list = dayOfWeekTypes.map { item ->
                            SelectButtonContent(
                                isSelected = item.isSelected,
                                text = item.type.displayingName,
                                onClick = { viewModel.selectDayOfWeek(item) }
                            )
                        })
                } else {
                    SelectScopeBox(
                        modifier = Modifier.fillMaxWidth(),
                        label = "날짜",
                        option1Text = startDate?.let { DateTimeUtils.getLocalDateText(it) }
                            ?: "오는날짜",
                        option2Text = endDate?.let { DateTimeUtils.getLocalDateText(it) }
                            ?: "내일날짜",
                        onOption1Clicked = { startDatePicker.show() },
                        onOption2Clicked = { endDatePicker.show() },
                        isOption1Selected = startDate != null,
                        isOption2Selected = endDate != null,
                        isChecked = false,
                        onCheckedChange = {}
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                )
                SelectScopeBox(
                    modifier = Modifier.fillMaxWidth(),
                    label = "시간",
                    option1Text = startTime?.let { DateTimeUtils.getLocalTimeText(it) }
                        ?: "시작시간",
                    option2Text = endTime?.let { DateTimeUtils.getLocalTimeText(it) } ?: "종료시간",
                    onOption1Clicked = { startTimePicker.show() },
                    onOption2Clicked = { endTimePicker.show() },
                    isChecked = false,
                    isOption1Selected = startTime != null,
                    isOption2Selected = endTime != null,
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
                    value = userInfo?.emd?.fullName ?: "",
                    isSelected = userInfo?.emd != null,
                    onClickSelection = {
                        NavigationUtils.navigate(navController, LocationSearchNav.route)
                    }
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Grey50)
                    .padding(20.dp)
            )


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
                            onClick = { viewModel.selectSalaryType(type) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                TextInpuField(
                    label = salaryTypes.find { it.isSelected }?.salaryType?.value ?: "시급",
                    value = salary?.let { NumberUtils.getPriceString(it) } ?: "",
                    onValueChanged = { viewModel.setSalary(it) },
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
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 700.dp)
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        ImageInputField(
                            inputType = ImageInputFieldType.Add(
                                index = photos.size,
                                onClick = {
                                    PermissionUtils.checkAndRequestPermissions(
                                        context,
                                        permissions,
                                        launcher,
                                        onPermissionGranted = {
                                            takePhotoFromAlbumLauncher.launch("image/jpeg")
                                        }
                                    )
                                })
                        )
                    }
                    items(photos) { uri ->
                        ImageInputField(
                            inputType = ImageInputFieldType.Editable(
                                imageUri = uri
                            )
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Grey50)
                    .padding(20.dp)
            )

            SubtextBox(
                modifier = Modifier
                    .fillMaxWidth(),
                titleText = "기타조건",
                subtitleText = "(선택)",
                size = SubtextBoxSize.S
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        top = 16.dp,
                        bottom = 40.dp,
                        end = 24.dp
                    )
                    .heightIn(max = 700.dp)
                    .wrapContentHeight(),
            ) {
                items(etcCheckList.size) { index ->
                    CheckBoxListItem(
                        checked = etcCheckList[index].isChecked,
                        onCheckedChange = { viewModel.checkEtcListItem(etcCheckList[index]) },
                        text = etcCheckList[index].displayName
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Grey100)
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


private fun createDatePicker(
    calendar: Calendar,
    context: Context,
    onDateSelected: (Int, Int, Int) -> Unit
): DatePickerDialog {
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    return DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            onDateSelected(selectedYear, selectedMonth, selectedDayOfMonth)
        },
        year, month, dayOfMonth
    )
}

private fun createTimePickerDialog(
    calendar: Calendar,
    context: Context,
    onTimeSelected: (Int, Int) -> Unit
): TimePickerDialog {
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    return TimePickerDialog(
        context,
        { _, selectedHour: Int, selectedMinute: Int ->
            onTimeSelected(selectedHour, selectedMinute)
        }, hour, minute, false
    )
}


