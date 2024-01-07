package com.mommydndn.app.ui.care.agency

import android.net.Uri
import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.OtherOption
import com.mommydndn.app.domain.model.user.Neighborhood

data class PostCareAgencyProfile(
    val coverPhotos: List<Uri>,
    val profilePhoto: Uri,
    val bio: String,
    val neighborhood: Neighborhood,
    val careTypes: List<CareType>,
    val otherOptions: List<OtherOption>,
)

interface PostCareAgencyProfileDelegate {
}