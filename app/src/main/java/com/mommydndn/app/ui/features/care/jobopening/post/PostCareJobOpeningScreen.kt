package com.mommydndn.app.ui.features.care.jobopening.post

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.WorkPeriod
import com.mommydndn.app.ui.components.button.NextButton
import com.mommydndn.app.ui.components.common.TopAppBarHeight
import com.mommydndn.app.ui.features.care.jobopening.post.component.ContentTextField
import com.mommydndn.app.ui.features.care.jobopening.post.component.PostCareJobOpeningCareTypes
import com.mommydndn.app.ui.features.care.jobopening.post.component.PostCareJobOpeningDatePicker
import com.mommydndn.app.ui.features.care.jobopening.post.component.PostCareJobOpeningPhotos
import com.mommydndn.app.ui.features.care.jobopening.post.component.PostCareJobOpeningTopAppBar
import com.mommydndn.app.ui.features.care.jobopening.post.component.PostCareJobOpeningWorkDateTimes
import com.mommydndn.app.ui.features.care.jobopening.post.component.PostCareJobOpeningWorkPlace
import com.mommydndn.app.ui.features.care.jobopening.post.component.TitleTextField
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.White
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
internal fun PostCareJobOpeningRoute(
    onCloseClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PostCareJobOpeningViewModel = hiltViewModel(),
) {
    val title by viewModel.title.collectAsStateWithLifecycle()
    val content by viewModel.content.collectAsStateWithLifecycle()
    val careTypes by viewModel.careTypes.collectAsStateWithLifecycle()
    val workDateTimes by viewModel.workDateTimes.collectAsStateWithLifecycle()
    val workPlace by viewModel.workPlace.collectAsStateWithLifecycle()
    val photoUris by viewModel.photoUris.collectAsStateWithLifecycle()

    val openDatePicker = remember {
        mutableStateOf(false)
    }

    PostCareJobOpeningScreen(
        onCloseClick = onCloseClick,
        onLoadClick = {},
        title = title,
        onTitleChange = viewModel::setTitle,
        content = content,
        onContentChange = viewModel::setContent,
        careTypes = careTypes,
        onCareTypeClick = { careType ->
            careTypes.toMutableList().apply {
                if (contains(careType)) {
                    remove(careType)
                } else {
                    add(careType)
                }
            }.let {
                viewModel.setCareTypes(it)
            }
        },
        workDateTimes = workDateTimes,
        onWorkPeriodSelected = { workPeriod ->
            viewModel.setWorkDateTimes(
                workDateTimes.copy(
                    workPeriod = workPeriod,
                )
            )
        },
        onAddDateClick = {
            openDatePicker.value = true
        },
        openDatePicker = openDatePicker.value,
        onDismissClick = {
            openDatePicker.value = false
        },
        onDateSelected = { date ->
            workDateTimes.copy(
                dates = workDateTimes.dates
                    .toMutableList()
                    .apply { add(date) },
            ).let {
                viewModel.setWorkDateTimes(it)
            }
        },
        onRemoveDateClick = { date ->
            workDateTimes.copy(
                dates = workDateTimes.dates
                    .toMutableList()
                    .apply { remove(date) }
            ).let {
                viewModel.setWorkDateTimes(it)
            }
        },
        onDayOfWeekClick = { dayOfWeek ->
            workDateTimes.copy(
                daysOfWeek = workDateTimes.daysOfWeek
                    .toMutableList()
                    .apply {
                        if (contains(dayOfWeek)) {
                            remove(dayOfWeek)
                        } else {
                            add(dayOfWeek)
                        }
                    }
            ).let {
                viewModel.setWorkDateTimes(it)
            }
        },
        onStartDateClick = {},
        onEndDateClick = {},
        onStartTimeClick = {},
        onEndTimeClick = {},
        onNegotiableClick = {
            viewModel.setWorkDateTimes(
                workDateTimes.copy(
                    negotiable = !workDateTimes.negotiable
                )
            )
        },
        workPlace = workPlace,
        photoUris = photoUris.map(Uri::parse),
        onNextClick = onNextClick,
        modifier = modifier,
    )
}

@Composable
internal fun PostCareJobOpeningScreen(
    onCloseClick: () -> Unit,
    onLoadClick: () -> Unit,
    title: String,
    onTitleChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit,
    careTypes: List<CareType>,
    onCareTypeClick: (CareType) -> Unit,
    workDateTimes: CareJobOpeningWorkDateTimes,
    onWorkPeriodSelected: (WorkPeriod) -> Unit,
    onAddDateClick: () -> Unit,
    openDatePicker: Boolean,
    onDateSelected: (LocalDate) -> Unit,
    onDismissClick: () -> Unit,
    onRemoveDateClick: (LocalDate) -> Unit,
    onDayOfWeekClick: (DayOfWeek) -> Unit,
    onStartDateClick: () -> Unit,
    onEndDateClick: () -> Unit,
    onStartTimeClick: () -> Unit,
    onEndTimeClick: () -> Unit,
    onNegotiableClick: () -> Unit,
    workPlace: CareJobOpeningWorkPlace,
    photoUris: List<Uri>,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (openDatePicker) {
        PostCareJobOpeningDatePicker(
            onDateSelected = onDateSelected,
            onDismissClick = onDismissClick,
            modifier = Modifier,
        )
    }

    Column(modifier = modifier) {
        PostCareJobOpeningTopAppBar(
            onCloseClick = onCloseClick,
            onLoadClick = onLoadClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(TopAppBarHeight)
                .background(White),
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .background(Grey50),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            item {
                Column(modifier = Modifier.background(White).padding(horizontal = 24.dp)) {
                    TitleTextField(
                        title = title,
                        onTitleChange = onTitleChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                    )

                    Divider(
                        modifier = Modifier
                            .background(White)
                            .padding(vertical = 8.dp),
                        thickness = 1.5.dp,
                        color = Grey50,
                    )

                    ContentTextField(
                        content = content,
                        onContentChange = onContentChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                    )
                }
            }

            item {
                PostCareJobOpeningCareTypes(
                    selectedCareTypes = careTypes,
                    onCareTypeClick = onCareTypeClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {
                PostCareJobOpeningWorkDateTimes(
                    selectedWorkPeriod = workDateTimes.workPeriod,
                    onWorkPeriodSelected = onWorkPeriodSelected,
                    selectedDates = workDateTimes.dates,
                    onAddDateClick = onAddDateClick,
                    onRemoveDateClick = onRemoveDateClick,
                    selectedDaysOfWeek = workDateTimes.daysOfWeek,
                    onDayOfWeekClick = onDayOfWeekClick,
                    startDate = workDateTimes.startDate,
                    endDate = workDateTimes.endDate,
                    onStartDateClick = onStartDateClick,
                    onEndDateClick = onEndDateClick,
                    startTime = workDateTimes.startTime,
                    endTime = workDateTimes.endTime,
                    onStartTimeClick = onStartTimeClick,
                    onEndTimeClick = onEndTimeClick,
                    negotiable = workDateTimes.negotiable,
                    onNegotiableClick = onNegotiableClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {
                PostCareJobOpeningWorkPlace(
                    address = workPlace.address.orEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }

            item {
                PostCareJobOpeningPhotos(
                    uris = photoUris,
                    onPhotosAdded = {},
                    onRemoveClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            }
        }

        NextButton(onClick = onNextClick, modifier = Modifier)
    }
}

@Preview
@Composable
private fun PostCareJobOpeningScreen_Preview() {
    PostCareJobOpeningRoute(
        onCloseClick = {},
        onNextClick = {},
        modifier = Modifier.fillMaxSize(),
        viewModel = PostCareJobOpeningViewModel()
    )
}