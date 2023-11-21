package com.mommydndn.app.ui.features.care.joboffer.write

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mommydndn.app.R
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.JobOfferPreview
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.common.ButtonColor
import com.mommydndn.app.data.model.common.ButtonColorType
import com.mommydndn.app.data.model.common.ButtonSizeType
import com.mommydndn.app.data.model.common.DayOfWeekItem
import com.mommydndn.app.data.model.common.ImageInputFieldType
import com.mommydndn.app.data.model.common.MinMaxRange
import com.mommydndn.app.data.model.common.SelectButtonContent
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.ui.components.box.DateBox
import com.mommydndn.app.ui.components.box.PostTextFieldBox
import com.mommydndn.app.ui.components.box.SelectButtonScopeBox
import com.mommydndn.app.ui.components.box.SelectScopeBox
import com.mommydndn.app.ui.components.box.SubtextBox
import com.mommydndn.app.ui.components.box.SubtextBoxSize
import com.mommydndn.app.ui.components.button.MommyDndnButton
import com.mommydndn.app.ui.components.chip.ClickableChip
import com.mommydndn.app.ui.components.list.CheckBoxListItem
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.inputfield.ImageInputField
import com.mommydndn.app.ui.components.inputfield.SelectField
import com.mommydndn.app.ui.components.inputfield.TextInpuField
import com.mommydndn.app.ui.extensions.addFocusCleaner
import com.mommydndn.app.ui.navigation.JobOfferLocationSearchNav
import com.mommydndn.app.ui.navigation.JobOfferWritePreviewNav
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400
import com.mommydndn.app.utils.DateTimeUtils
import com.mommydndn.app.utils.NavigationUtils
import com.mommydndn.app.utils.NumberCommaVisualTransformation
import com.mommydndn.app.utils.NumberUtils
import com.mommydndn.app.utils.PermissionUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar


@Composable
fun JobOfferWriteScreen(
    navController: NavHostController,
    viewModel: JobOfferWriteViewModel,
    scaffoldState: ScaffoldState
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    val emdItem by viewModel.emdItem.collectAsState()

    val careTypes by viewModel.careTypes.collectAsState()
    val workPeriodTypes by viewModel.workPeriodTypes.collectAsState()
    val salaryTypes by viewModel.salaryTypes.collectAsState()
    val dayOfWeekTypes by viewModel.dayOfWeekTypes.collectAsState()

    val title by viewModel.title.collectAsState()
    val content by viewModel.content.collectAsState()

    val dateList by viewModel.dateList.collectAsState()

    val startDate by viewModel.startDate.collectAsState()
    val endDate by viewModel.endDate.collectAsState()

    val startTime by viewModel.startTime.collectAsState()
    val endTime by viewModel.endTime.collectAsState()

    val locationInfo by viewModel.locationInfo.collectAsState()

    val salary by viewModel.salary.collectAsState()

    val photos by viewModel.photos.collectAsState()

    val minHourlySalary by viewModel.minHourlySalary.collectAsState()

    val etcCheckList by viewModel.etcCheckList.collectAsState()

    val datePicker = createDatePicker(
        calendar = calendar,
        context = context
    ) { year, month, dayOfMonth ->
        val selectedDate = LocalDate.of(year, month, dayOfMonth)
        val isDuplicate = dateList.any { it.isEqual(selectedDate) }

        if (isDuplicate) coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar("동일한 날짜가 존재합니다.")
        } else viewModel.addDate(year, month, dayOfMonth)
    }

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

    startDatePicker.datePicker.minDate = calendar.timeInMillis
    endDatePicker.datePicker.minDate = calendar.timeInMillis

    val takePhotoFromAlbumLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            viewModel.addSelectedPhotos(uriList)
        }

    datePicker.datePicker.minDate = calendar.timeInMillis

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
            .addFocusCleaner(focusManager)
    ) {
        Header(leftContent = {
            Image(
                painter = painterResource(id = R.drawable.ic_x),
                contentDescription = "",
                modifier = Modifier
                    .size(size = 36.dp)
                    .clickable { navController.popBackStack() }
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

            Divider(
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
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
            Divider(
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
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
                    workPeriodTypes.forEach { type ->
                        ClickableChip(
                            modifier = Modifier.height(36.dp),
                            text = type.workPeriodType.value,
                            selected = type.isSelected,
                            onClick = { viewModel.selectWorkPeriodType(type) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }

                if (workPeriodTypes.find { it.isSelected }?.workPeriodType == WorkPeriodType.REGULAR) {
                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "특정 주기마다 돌봄이 필요한 경우, 정기를 사용해요",
                        style = MaterialTheme.typography.caption200.copy(
                            fontWeight = FontWeight.Normal,
                            color = Grey500
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    SelectButtonScopeBox(
                        label = "요일",
                        list = dayOfWeekTypes.map { item ->
                            SelectButtonContent(
                                isSelected = item.isSelected,
                                text = item.type.displayingName,
                                onClick = { viewModel.selectDayOfWeek(item) }
                            )
                        })

                    Spacer(modifier = Modifier.height(24.dp))

                    SelectScopeBox(
                        modifier = Modifier.fillMaxWidth(),
                        label = "날짜",
                        option1Text = startDate?.let { DateTimeUtils.formatLocalDateToDotString(it) }
                            ?: "오는날짜",
                        option2Text = endDate?.let { DateTimeUtils.formatLocalDateToDotString(it) }
                            ?: "내일날짜",
                        onOption1Clicked = { startDatePicker.show() },
                        onOption2Clicked = { endDatePicker.show() },
                        isOption1Selected = startDate != null,
                        isOption2Selected = endDate != null,
                        isChecked = false,
                        onCheckedChange = {}
                    )

                } else {
                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "특정 날짜에 돌봄이 필요한 경우, 단기를 사용해요",
                        style = MaterialTheme.typography.caption200.copy(
                            fontWeight = FontWeight.Normal,
                            color = Grey500
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "날짜",
                        style = MaterialTheme.typography.paragraph300.copy(
                            fontWeight = FontWeight.Bold,
                            color = Grey500
                        )
                    )

                    Spacer(modifier = Modifier.padding(6.dp))

                    MommyDndnButton(
                        color = ButtonColor.SALMON,
                        colorType = ButtonColorType.WEAK,
                        rangeType = MinMaxRange.MAX,
                        text = "날짜 추가",
                        onClick = { datePicker.show() },
                        iconResourceId = R.drawable.ic_plus
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 700.dp)
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(dateList.size) { index ->
                            DateBox(
                                modifier = Modifier.weight(1f),
                                text = DateTimeUtils.formatLocalDateToDotString(dateList.get(index)),
                                isSelected = true,
                                onClick = {
                                    viewModel.removeDate(index)
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                SelectScopeBox(
                    modifier = Modifier.fillMaxWidth(),
                    label = "시간",
                    option1Text = startTime?.let { DateTimeUtils.getLocalTimeText(it) }
                        ?: "시작시간",
                    option2Text = endTime?.let { DateTimeUtils.getLocalTimeText(it) } ?: "종료시간",
                    onOption1Clicked = { startTimePicker.show() },
                    onOption2Clicked = { endTimePicker.show() },
                    isOption1Selected = startTime != null,
                    isOption2Selected = endTime != null
                )
            }
            Divider(
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
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
                    value = emdItem?.fullName ?: "주소를 선택해주세요.",
                    isSelected = emdItem != null,
                    onClickSelection = {
                        NavigationUtils.navigate(navController, JobOfferLocationSearchNav.route)
                    }
                )
            }
            Divider(
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
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

                var isSalaryBelowMin by remember { mutableStateOf(false) }
                val minSalary = minHourlySalary?.minHourlySalary

                if (minSalary != null && salary != null) {
                    if (minSalary > salary!!) isSalaryBelowMin = true
                    else isSalaryBelowMin = false
                }

                val salaryDescription = if (isSalaryBelowMin) {
                    "시급이 최저시급보다 낮습니다."
                } else {
                    minSalary?.let {
                        "2023년 최저시급은 " + NumberUtils.getPriceString(it) + "원이에요"
                    } ?: ""
                }

                val selectedSalaryType = salaryTypes.find { it.isSelected }

                if (selectedSalaryType?.salaryType != SalaryType.NEGOTIATION) {
                    TextInpuField(
                        modifier = Modifier.fillMaxWidth(),
                        label = selectedSalaryType?.salaryType?.value ?: "시급",
                        value = salary?.let { it.toString() } ?: "",
                        onValueChanged = { value ->
                            viewModel.setSalary(value)
                        },
                        rightText = "원",
                        placeHolderText = "10,000",
                        descriptionText = if (selectedSalaryType?.salaryType != SalaryType.NEGOTIATION) salaryDescription else "",
                        focusRequester = focusRequester,
                        isError = isSalaryBelowMin,
                        visualTransformation = NumberCommaVisualTransformation(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }

            }

            Divider(
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
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

                    items(listOf(1, 2, 3)) {
                        Box(modifier = Modifier.size(0.dp))
                    } // LazyVerticalGrid 첫번째 줄 삭제버튼 잘려서 보이는 오류로 인해 해당 코드 추가

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
                                imageUri = uri,
                                onRemoveClick = { viewModel.removePhoto(uri) }
                            )
                        )
                    }
                }
            }
            Divider(
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
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
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                MommyDndnButton(
                    text = "다음으로",
                    color = ButtonColor.SALMON,
                    colorType = ButtonColorType.FILLED,
                    sizeType = ButtonSizeType.LARGE,
                    rangeType = MinMaxRange.MAX,
                    onClick = {
                        val isSuccessful = isValidationSuccessful(
                            title = title,
                            emdItem = emdItem,
                            careTypes = careTypes,
                            salary = salary,
                            dayOfWeekTypes = dayOfWeekTypes.filter { it.isSelected },
                            minHourlySalary = minHourlySalary,
                            coroutineScope = coroutineScope,
                            scaffoldState = scaffoldState,
                            salaryType = salaryTypes.filter { it.isSelected }.map { it.salaryType }
                                .first(),
                            startTime = startTime,
                            endTime = endTime,
                            dateList = dateList,
                            workPeriodType = workPeriodTypes.filter { it.isSelected }
                                .map { it.workPeriodType }.first()
                        )

                        if (isSuccessful) {
                            NavigationUtils.navigate(
                                navController, JobOfferWritePreviewNav.navigateWithArg(
                                    JobOfferPreview(
                                        title = title,
                                        content = content,
                                        caringTypeList = careTypes.filter { it.isSelected }
                                            .map { it.caringType },
                                        taskType = workPeriodTypes.filter { it.isSelected }
                                            .map { it.workPeriodType }.first(),
                                        dateList = dateList,
                                        days = dayOfWeekTypes.filter { it.isSelected },
                                        startTime = startTime,
                                        endTime = endTime,
                                        emd = emdItem!!,
                                        latitude = locationInfo?.latitude!!,
                                        longitude = locationInfo?.longitude!!,
                                        salaryType = salaryTypes.filter { it.isSelected }
                                            .map { it.salaryType }.first(),
                                        salary = salary ?: 0,
                                        etcCheckedList = etcCheckList.filter { it.isChecked },
                                        imageList = photos.map {
                                            Uri.encode(it.toString())
                                        },
                                        startDate = startDate,
                                        endDate = endDate
                                    )
                                )
                            )
                        }
                    }
                )
            }
        }
    }
}

fun isValidationSuccessful(
    title: String,
    emdItem: EmdItem?,
    careTypes: List<CaringTypeItem>,
    dateList: List<LocalDate>,
    workPeriodType: WorkPeriodType,
    salary: Int?,
    dayOfWeekTypes: List<DayOfWeekItem>,
    salaryType: SalaryType,
    minHourlySalary: MinHourlySalary?,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    startTime: LocalTime,
    endTime: LocalTime
): Boolean {

    if (minHourlySalary == null) {
        return false
    }

    val errorMessage = if (title.isBlank()) {
        "제목을 입력해주세요."
    } else if (careTypes.all { !it.isSelected }) {
        "돌봄 종류를 선택해주세요."
    } else if (workPeriodType == WorkPeriodType.ONETIME && dateList.isEmpty()) {
        "날짜를 선택해주세요."
    } else if (workPeriodType == WorkPeriodType.REGULAR && dayOfWeekTypes.isEmpty()) {
        "돌봄 요일을 선택해주세요."
    } else if (endTime.isBefore(startTime) || (startTime.hour == endTime.hour && startTime.minute == endTime.minute)) {
        "종료 시간을 확인해주세요."
    } else if (emdItem == null) {
        "일하는 장소를 선택해주세요."
    } else if (salaryType != SalaryType.NEGOTIATION && salary == null) {
        "임금이 입력되지 않았습니다."
    } else if (salaryType != SalaryType.NEGOTIATION && salary!! < minHourlySalary.minHourlySalary) {
        "임금은 최저시급 보다 높아야 합니다."
    } else null

    if (errorMessage == null) {
        return true
    } else {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(errorMessage)
        }
        return false
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
            onDateSelected(selectedYear, selectedMonth + 1, selectedDayOfMonth)
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


