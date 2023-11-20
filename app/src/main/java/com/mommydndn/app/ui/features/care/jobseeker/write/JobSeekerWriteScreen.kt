package com.mommydndn.app.ui.features.care.jobseeker.write

import android.Manifest
import android.net.Uri
import android.os.Build
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mommydndn.app.R
import com.mommydndn.app.data.model.care.CaringTypeItem
import com.mommydndn.app.data.model.care.JobSeekerPreview
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.common.ButtonColor
import com.mommydndn.app.data.model.common.ButtonColorType
import com.mommydndn.app.data.model.common.ButtonSizeType
import com.mommydndn.app.data.model.common.ImageInputFieldType
import com.mommydndn.app.data.model.common.MinMaxRange
import com.mommydndn.app.data.model.map.EmdItem
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
import com.mommydndn.app.ui.navigation.JobSeekerLocationSearchNav
import com.mommydndn.app.ui.navigation.JobSeekerWritePreviewNav
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph400
import com.mommydndn.app.utils.NavigationUtils
import com.mommydndn.app.utils.NumberUtils
import com.mommydndn.app.utils.PermissionUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun JobSeekerWriteScreen(
    navController: NavHostController,
    viewModel: JobSeekerWriteViewModel,
    scaffoldState: ScaffoldState
) {
    val context = LocalContext.current

    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    val emdItem by viewModel.emdItem.collectAsState()

    val careTypes by viewModel.careTypes.collectAsState()
    val salaryTypes by viewModel.salaryTypes.collectAsState()

    val introduce by viewModel.introduce.collectAsState()

    val salary by viewModel.salary.collectAsState()

    val photo by viewModel.photo.collectAsState()

    val minHourlySalary by viewModel.minHourlySalary.collectAsState()

    val etcCheckList by viewModel.etcCheckList.collectAsState()

    val takePhotoFromAlbumLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { viewModel.addSelectedPhotos(uri) }
        }

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
                text = "구직 프로필 쓰기",
                style = MaterialTheme.typography.paragraph400.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey700
                )
            )
        })

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {

            SubtextBox(
                modifier = Modifier
                    .fillMaxWidth(),
                titleText = "프로필 사진",
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
                if (photo == null) {
                    ImageInputField(
                        inputType = ImageInputFieldType.Add(
                            index = 0,
                            onClick = {
                                PermissionUtils.checkAndRequestPermissions(
                                    context,
                                    permissions,
                                    launcher,
                                    onPermissionGranted = {
                                        takePhotoFromAlbumLauncher.launch("image/jpeg")
                                    }
                                )
                            },
                            isCaptionVisible = false
                        )
                    )
                } else {
                    ImageInputField(
                        inputType = ImageInputFieldType.Ineditable(
                            imageUri = photo!!
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
                titleText = "한 줄 소개",
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
                TextInpuField(
                    modifier = Modifier.fillMaxWidth(),
                    value = introduce,
                    onValueChanged = { value -> viewModel.setIntroduce(value) },
                    placeHolderText = "시터님을 소개하는 한 마디를 작성해주세요!",
                    focusRequester = focusRequester,
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
                titleText = "활동 가능한 동네",
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
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SelectField(
                        modifier = Modifier.fillMaxWidth(),
                        label = "내 동네",
                        value = emdItem?.fullName ?: "",
                        isSelected = emdItem != null,
                        onClickSelection = {
                            NavigationUtils.navigate(
                                navController,
                                JobSeekerLocationSearchNav.route
                            )
                        }
                    )
                    Text(
                        text = "가락동 외 근처 동네 24개",
                        style = MaterialTheme.typography.caption200.copy(
                            fontWeight = FontWeight.Medium,
                            color = Grey600
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
                titleText = "하고싶은 돌봄분야",
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
                titleText = "든든시터 인증",
                subtitleText = "(선택)",
                size = SubtextBoxSize.S
            )

            Box(
                modifier = Modifier.padding(
                    top = 16.dp,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 40.dp
                )
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    MommyDndnButton(
                        color = ButtonColor.SALMON,
                        colorType = ButtonColorType.WEAK,
                        rangeType = MinMaxRange.MAX,
                        sizeType = ButtonSizeType.MEDIUM,
                        text = "추가 인증하기"
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
                titleText = "희망급여",
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
                        placeHolderText = "10,000",
                        descriptionText = if (selectedSalaryType?.salaryType != SalaryType.NEGOTIATION) salaryDescription else "",
                        focusRequester = focusRequester,
                        isError = isSalaryBelowMin
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
                titleText = "기타사항",
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
                            introduce = introduce,
                            emdItem = emdItem,
                            careTypes = careTypes.filter { it.isSelected },
                            salary = salary,
                            salaryType = salaryTypes.filter { it.isSelected }.first().salaryType,
                            minHourlySalary = minHourlySalary,
                            photo = photo,
                            coroutineScope = coroutineScope,
                            scaffoldState = scaffoldState
                        )

                        if (isSuccessful) {
                            NavigationUtils.navigate(
                                navController, JobSeekerWritePreviewNav.navigateWithArg(
                                    JobSeekerPreview(
                                        introduce = introduce,
                                        caringTypeList = careTypes.filter { it.isSelected }
                                            .map { it.caringType },
                                        emd = emdItem!!,
                                        salaryType = salaryTypes.filter { it.isSelected }
                                            .first().salaryType,
                                        salary = salary!!,
                                        etcCheckedList = etcCheckList,
                                        imageUri = Uri.encode(photo.toString())
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
    introduce: String,
    emdItem: EmdItem?,
    careTypes: List<CaringTypeItem>,
    salary: Int?,
    salaryType: SalaryType,
    minHourlySalary: MinHourlySalary?,
    photo: Uri?,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
): Boolean {

    if (minHourlySalary == null) {
        return false
    }

    val errorMessage = if (photo == null) {
        "프로필 사진을 등록해주세요."
    } else if (introduce.isBlank()) {
        "시터님 소개를 입력해주세요."
    } else if (careTypes.all { !it.isSelected }) {
        "돌봄 종류를 선택해주세요."
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

