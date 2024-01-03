package com.mommydndn.app.ui.deprecated.companywrite

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
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.common.ButtonColor
import com.mommydndn.app.data.model.common.ButtonColorType
import com.mommydndn.app.data.model.common.ButtonSizeType
import com.mommydndn.app.data.model.common.ImageInputFieldType
import com.mommydndn.app.data.model.common.MinMaxRange
import com.mommydndn.app.data.model.location.EmdItem
import com.mommydndn.app.ui.deprecated.components.box.SubtextBox
import com.mommydndn.app.ui.deprecated.components.box.SubtextBoxSize
import com.mommydndn.app.ui.deprecated.components.button.MommyDndnButton
import com.mommydndn.app.ui.deprecated.components.chip.ClickableChip
import com.mommydndn.app.ui.deprecated.components.common.Header
import com.mommydndn.app.ui.deprecated.components.inputfield.ImageInputField
import com.mommydndn.app.ui.deprecated.components.inputfield.SelectField
import com.mommydndn.app.ui.deprecated.components.inputfield.TextInpuField
import com.mommydndn.app.ui.deprecated.components.inputfield.TextInputScopeBox
import com.mommydndn.app.ui.deprecated.components.list.CheckBoxListItem
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph400
import com.mommydndn.app.util.NumberCommaVisualTransformation
import com.mommydndn.app.util.PermissionUtils
import com.mommydndn.app.util.extensions.addFocusCleaner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CompanyWriteScreen(
    navController: NavHostController,
    viewModel: CompanyWriteViewModel,
    scaffoldState: ScaffoldState
) {
    val context = LocalContext.current

    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    val focusManager = LocalFocusManager.current

    val emdItem by viewModel.emdItem.collectAsState()
    val locationInfo by viewModel.neighborhood.collectAsState()

    val careTypes by viewModel.careTypes.collectAsState()

    val introduce by viewModel.introduce.collectAsState()

    val startSalary by viewModel.startSalary.collectAsState()
    val endSalary by viewModel.endSalary.collectAsState()

    val photo by viewModel.photo.collectAsState()

    val photos by viewModel.photos.collectAsState()

    val commission by viewModel.commission.collectAsState()

    val minHourlySalary by viewModel.minHourlySalary.collectAsState()

    val etcCheckList by viewModel.etcCheckList.collectAsState()

    val coverPhotosFromAlbumLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { list ->
            list?.let { viewModel.addSelectedPhotos(list) }
        }

    val profilePhotoFromAlbumLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { viewModel.addSelectedPhoto(uri) }
        }

    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
    } else {
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    val profilePermssionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            Log.d("JobOfferWriteScreen", "권한이 동의되었습니다.")
            profilePhotoFromAlbumLauncher.launch("image/jpeg")
        } else {
        }
        Log.d("JobOfferWriteScreen", "권한이 거부되었습니다.")
    }

    val ceverPermssionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            Log.d("JobOfferWriteScreen", "권한이 동의되었습니다.")
            coverPhotosFromAlbumLauncher.launch("image/jpeg")
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
                painter = painterResource(id = R.drawable.icon_close),
                contentDescription = "",
                modifier = Modifier
                    .size(size = 36.dp)
                    .clickable { navController.popBackStack() }
            )
        }, centerContent = {
            Text(
                text = "업체 프로필 쓰기",
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
                title = "커버 사진",
                subtitle = "(필수)",
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
                                        ceverPermssionLauncher,
                                        onPermissionGranted = {
                                            coverPhotosFromAlbumLauncher.launch("image/jpeg")
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
                title = "프로필 사진",
                subtitle = "(필수)",
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
                                    profilePermssionLauncher,
                                    onPermissionGranted = {
                                        profilePhotoFromAlbumLauncher.launch("image/jpeg")
                                    }
                                )
                            },
                            isCaptionVisible = false
                        )
                    )
                } else {
                    ImageInputField(
                        inputType = ImageInputFieldType.Ineditable(
                            imageUri = photo!!,
                            onClick = {
                                PermissionUtils.checkAndRequestPermissions(
                                    context,
                                    permissions,
                                    profilePermssionLauncher,
                                    onPermissionGranted = {
                                        profilePhotoFromAlbumLauncher.launch("image/jpeg")
                                    }
                                )
                            }
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
                title = "한 줄 소개",
                subtitle = "(필수)",
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
                    placeHolderText = "업체를 소개하는 한 마디를 작성해주세요!",
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
                title = "활동 가능한 동네",
                subtitle = "(필수)",
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

                        }
                    )
                    Text(
                        text = "${emdItem?.name} 외 근처 동네 24개",
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
                title = "하고싶은 돌봄분야",
                subtitle = "(필수)",
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
                title = "평균월급",
                subtitle = "(필수)",
                size = SubtextBoxSize.S
            )

            var isMaxSalaryBelowMinSalary by remember { mutableStateOf(false) }

            if (startSalary != null && endSalary != null) {
                if (endSalary!! < startSalary!!) isMaxSalaryBelowMinSalary = true
                else isMaxSalaryBelowMinSalary = false
            }

            Column(
                modifier = Modifier.padding(
                    start = 24.dp,
                    top = 16.dp,
                    bottom = 40.dp,
                    end = 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                TextInputScopeBox(
                    modifier = Modifier.fillMaxWidth(),
                    label = "월급",
                    value1 = startSalary?.let { it.toString() } ?: "",
                    value2 = endSalary?.let { it.toString() } ?: "",
                    onValue1Changed = { viewModel.setStartSalary(it) },
                    onValue2Changed = { viewModel.setEndSalary(it) },
                    placeHolder1Text = "최소",
                    placeHolder2Text = "최대",
                    option1FocusRequester = focusRequester,
                    option2FocusRequester = focusRequester,
                    visualTransformation = NumberCommaVisualTransformation(),
                    isError = isMaxSalaryBelowMinSalary,
                    descriptionText = if (isMaxSalaryBelowMinSalary) "최소 월급을 확인해주세요" else ""
                )

                TextInpuField(
                    modifier = Modifier.fillMaxWidth(),
                    value = commission?.let { it.toString() } ?: "",
                    label = "수수료",
                    rightText = "%",
                    onValueChanged = { value -> viewModel.setCommission(value.toInt()) },
                    placeHolderText = "최소",
                    focusRequester = focusRequester,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
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
                title = "기타사항",
                subtitle = "(선택)",
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
                            coverImageList = photos,
                            introduce = introduce,
                            careTypes = careTypes.filter { it.isSelected },
                            emdItem = emdItem,
                            startSalary = startSalary,
                            endSalary = endSalary,
                            commission = commission,
                            profileImage = photo,
                            minHourlySalary = minHourlySalary,
                            coroutineScope = coroutineScope,
                            scaffoldState = scaffoldState,
                        )

                        if (isSuccessful) {
                            /*
                            NavigationUtils.navigate(
                                navController,
                                CompanyWritePreviewNav.navigateWithArg(
                                    CompanyPreview(
                                        introduce = introduce,
                                        caringTypeList = careTypes.filter { it.isSelected }
                                            .map { it.caringType },
                                        startSalary = startSalary!!,
                                        endSalary = endSalary!!,
                                        etcCheckedList = etcCheckList.map {
                                            it.copy(displayName = Uri.encode(it.displayName))
                                        },
                                        profileImage = Uri.encode(photo.toString()),
                                        commission = commission!!,
                                        coverImageList = photos.map { Uri.encode(it.toString()) },
                                        neighborhood = locationInfo
                                    )
                                )
                            )
                             */

                        }

                    }
                )
            }
        }
    }
}

private fun isValidationSuccessful(
    coverImageList: List<Uri>,
    introduce: String?,
    careTypes: List<CaringTypeItem>,
    emdItem: EmdItem?,
    startSalary: Int?,
    endSalary: Int?,
    commission: Int?,
    profileImage: Uri?,
    minHourlySalary: MinHourlySalary?,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
): Boolean {

    if (minHourlySalary == null) {
        return false
    }

    val errorMessage = if (coverImageList.isNullOrEmpty()) {
        "커버 사진은 필수입니다"
    } else if (profileImage == null) {
        "프로필을 등록해주세요"
    } else if (introduce.isNullOrEmpty()) {
        "한 줄 소개를 입력해주세요"
    } else if (emdItem == null) {
        "활동 가능한 동네를 선택해주세요"
    } else if (careTypes.isNullOrEmpty()) {
        "돌봄을 선택해주세요"
    } else if (startSalary == null || endSalary == null || startSalary > endSalary) {
        "월급을 확인해주세요"
    } else if (commission == null) {
        "수수료를 입력해주세요"
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
