package com.mommydndn.app.ui.care.provider

import com.mommydndn.app.domain.model.care.CareType
import com.mommydndn.app.domain.model.care.OtherOption
import com.mommydndn.app.domain.model.user.Neighborhood
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface PostCareProviderProfileDelegate {
    val photoUri: StateFlow<String?>
    val bio: StateFlow<String>
    val neighborhood: StateFlow<Neighborhood>
    val careTypes: StateFlow<List<CareType>>
    val authentications: StateFlow<List<String>>
    val options: StateFlow<List<OtherOption>>

    fun setPhotoUris(photoUri: String?)
    fun setBio(bio: String)
    fun setNeighborhood(neighborhood: Neighborhood)
    fun setCareTypes(careTypes: List<CareType>)
    fun setAuthentications(authentications: List<String>)
    fun setOptions(options: List<OtherOption>)
}

object PostCareProviderProfileViewModelDelegate : PostCareProviderProfileDelegate {

    private val _photoUri = MutableStateFlow<String?>(null)
    override val photoUri: StateFlow<String?> = _photoUri

    private val _bio = MutableStateFlow("")
    override val bio: StateFlow<String> = _bio

    private val _neighborhood = MutableStateFlow(Neighborhood())
    override val neighborhood: StateFlow<Neighborhood> = _neighborhood

    private val _careTypes = MutableStateFlow(emptyList<CareType>())
    override val careTypes: StateFlow<List<CareType>> = _careTypes

    private val _authentications = MutableStateFlow(emptyList<String>())
    override val authentications: StateFlow<List<String>> = _authentications

    private val _options = MutableStateFlow(emptyList<OtherOption>())
    override val options: StateFlow<List<OtherOption>> = _options

    override fun setPhotoUris(photoUri: String?) {
        _photoUri.value = photoUri
    }

    override fun setBio(bio: String) {
        _bio.value = bio
    }

    override fun setNeighborhood(neighborhood: Neighborhood) {
        _neighborhood.value = neighborhood
    }

    override fun setCareTypes(careTypes: List<CareType>) {
        _careTypes.value = careTypes
    }

    override fun setAuthentications(authentications: List<String>) {
        _authentications.value = authentications
    }

    override fun setOptions(options: List<OtherOption>) {
        _options.value = options
    }
}