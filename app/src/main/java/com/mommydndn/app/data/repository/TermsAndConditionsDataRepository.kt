package com.mommydndn.app.data.repository

import com.mommydndn.app.data.api.model.request.UpdateTermsAndConditionsRequest
import com.mommydndn.app.data.api.service.TermsAndConditionsService
import com.mommydndn.app.data.model.terms.TermsItem
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TermsAndConditionsDataRepository @Inject constructor(
    private val termsAndConditionsService: TermsAndConditionsService,
) : TermsAndConditionsRepository {

    override fun fetchAllTerms() = flow {
        termsAndConditionsService.fetchTermsItems().suspendOnSuccess {
            val list = data.map {
                TermsItem(
                    createdAt = it.createdAt,
                    isRequired = it.isRequired,
                    name = it.name,
                    termsId = it.termsId,
                    updateAt = it.updateAt,
                    url = it.url,
                    isSelected = false
                )
            }
            emit(list)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateTermsCheckedStatus(termsItems: List<TermsItem>) {
        val approvalRequestList = termsItems.map {
            UpdateTermsAndConditionsRequest(
                termsId = it.termsId,
                isApproved = it.isSelected
            )
        }
        termsAndConditionsService.updateTermsApproval(approvalRequestList)
    }

}