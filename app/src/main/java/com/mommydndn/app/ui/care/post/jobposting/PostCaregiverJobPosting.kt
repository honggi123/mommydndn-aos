package com.mommydndn.app.ui.care.post.jobposting

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.WorkPeriod
import com.mommydndn.app.ui.care.post.components.CareTypesPostSection
import com.mommydndn.app.ui.care.post.components.ContentTextField
import com.mommydndn.app.ui.care.post.components.GetPhotosPostSection
import com.mommydndn.app.ui.care.post.components.PostNextButton
import com.mommydndn.app.ui.care.post.components.PostTopAppBar
import com.mommydndn.app.ui.care.post.components.TitleTextField
import com.mommydndn.app.ui.care.post.components.WorkDateTimesPostSection
import com.mommydndn.app.ui.care.post.components.WorkPlacePostSection
import com.mommydndn.app.ui.components.date.PostDatePicker
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun PostCaregiverJobPosting(
    onCloseClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PostCaregiverJobPostingViewModel = hiltViewModel(),
) {
    /*
    val title by viewModel.title.collectAsStateWithLifecycle()
    val content by viewModel.content.collectAsStateWithLifecycle()
    val careTypes by viewModel.careTypes.collectAsStateWithLifecycle()
    val workDateTimes by viewModel.workDateTimes.collectAsStateWithLifecycle()
    val workPlace by viewModel.workPlace.collectAsStateWithLifecycle()
    val photoUris by viewModel.photoUris.collectAsStateWithLifecycle()

    viewModel.photoUris.collectAsStateWithLifecycle()

    val openDatePicker = remember {
        mutableStateOf(false)
    }

    PostCareJobContent(
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
                oneTimeDates = workDateTimes.oneTimeDates
                    .toMutableList()
                    .apply { add(date) },
            ).let {
                viewModel.setWorkDateTimes(it)
            }
        },
        onRemoveDateClick = { date ->
            workDateTimes.copy(
                oneTimeDates = workDateTimes.oneTimeDates
                    .toMutableList()
                    .apply { remove(date) }
            ).let {
                viewModel.setWorkDateTimes(it)
            }
        },
        onDayOfWeekClick = { dayOfWeek ->
            workDateTimes.copy(
                regularDaysOfWeek = workDateTimes.regularDaysOfWeek
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
        address = workPlace,
        photoUris = photoUris.map(Uri::parse),
        onPhotosChange = { uris ->
            viewModel.setPhotoUris(uris.map(Uri::toString))
        },
        onRemovePhotoClick = {},
        onNextClick = onNextClick,
        modifier = modifier,
    )
     */
}

@Composable
private fun PostCaregiverJobPostingContent(
    onCloseClick: () -> Unit,
    onLoadClick: () -> Unit,
    title: String,
    onTitleChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit,
    careTypes: List<CareType>,
    onCareTypeClick: (CareType) -> Unit,
    workDateTimes: CareJobWorkDateTimesUiModel,
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
    address: String,
    photoUris: List<Uri>,
    onPhotosChange: (List<Uri>) -> Unit,
    onRemovePhotoClick: (Uri) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (openDatePicker) {
        PostDatePicker(
            onDateSelected = onDateSelected,
            onDismissClick = onDismissClick,
            modifier = Modifier,
        )
    }

    Column(modifier = modifier) {
        PostTopAppBar(
            leading = {
                Icon(
                    painter = painterResource(id = R.drawable.icon_close),
                    contentDescription = "PostCareJobOpeningTopAppBar_Close",
                    modifier = Modifier
                        .size(size = 36.dp)
                        .padding(4.dp)
                        .clickable(onClick = onCloseClick),
                    tint = Grey300,
                )
            },
            onLeadingClick = onCloseClick,
            title = stringResource(R.string.post_job_opening),
            modifier = Modifier,
            trailing = {
                Text(
                    text = stringResource(R.string.load),
                    modifier = Modifier
                        .clickable(onClick = onCloseClick)
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    color = Grey500,
                    style = MaterialTheme.typography.paragraph300.merge(
                        fontWeight = FontWeight.Medium,
                    )
                )
            }, 
            onTrailingClick = onLoadClick,
        )

        Column(
            modifier = Modifier
                .background(Grey50)
                .fillMaxWidth()
                .weight(1F)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column(
                modifier = Modifier
                    .background(White)
                    .padding(horizontal = 24.dp)
            ) {
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

            CareTypesPostSection(
                selectedCareTypes = careTypes,
                onCareTypeClick = onCareTypeClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            )

            WorkDateTimesPostSection(
                selectedWorkPeriod = workDateTimes.workPeriod,
                onWorkPeriodSelected = onWorkPeriodSelected,
                selectedDates = workDateTimes.oneTimeDates,
                onAddDateClick = onAddDateClick,
                onRemoveDateClick = onRemoveDateClick,
                selectedDaysOfWeek = workDateTimes.regularDaysOfWeek,
                onDayOfWeekClick = onDayOfWeekClick,
                startDate = workDateTimes.regularStartDate,
                endDate = workDateTimes.regularEndDate,
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

            WorkPlacePostSection(
                address = address,
                onAddressClick = {}, // TODO
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            )

            GetPhotosPostSection(
                uris = photoUris,
                onPhotosChange = onPhotosChange,
                onRemoveClick = onRemovePhotoClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            )
        }

        PostNextButton(
            onClick = onNextClick,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        )
    }
}

@Preview
@Composable
private fun PostCareJobScreenPreview() {
    PostCaregiverJobPosting(
        onCloseClick = {},
        onNextClick = {},
        modifier = Modifier.fillMaxSize(),
        viewModel = PostCaregiverJobPostingViewModel()
    )
}