package com.mommydndn.app.data.api.model.response

import kotlinx.serialization.SerialName

data class JobSeekerCreationResponse(
    @SerialName("jobSeekerId")
    val jobSeekerId: Int
)
