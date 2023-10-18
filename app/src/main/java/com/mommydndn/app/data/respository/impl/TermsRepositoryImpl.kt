package com.mommydndn.app.data.respository.impl

import com.mommydndn.app.data.api.model.request.TermsApprovalRequest
import com.mommydndn.app.data.api.service.TermsService
import com.mommydndn.app.data.model.terms.TermsItem
import com.mommydndn.app.data.respository.TermsRepository
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TermsRepositoryImpl @Inject constructor(
    private val termsService: TermsService,
) : TermsRepository {
    override fun fetchAllTerms() = flow {
        termsService.fetchTermsItems().suspendOnSuccess {
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
            TermsApprovalRequest(
                termsId = it.termsId,
                isApproved = it.isSelected
            )
        }
        termsService.updateTermsApproval(approvalRequestList)
    }

}