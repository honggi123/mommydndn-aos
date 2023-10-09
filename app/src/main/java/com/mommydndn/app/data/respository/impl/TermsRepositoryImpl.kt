package com.mommydndn.app.data.respository.impl

import com.mommydndn.app.data.api.ErrorResponseMapper
import com.mommydndn.app.data.api.model.TermsApprovalRequest
import com.mommydndn.app.data.api.service.TermsService
import com.mommydndn.app.data.api.model.TermsItemResponse
import com.mommydndn.app.data.model.TermsItem
import com.mommydndn.app.data.respository.TermsRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class TermsRepositoryImpl @Inject constructor(
    private val termsService: TermsService,
) : TermsRepository {
    override fun fetchAllTerms(
        onComplete: () -> Unit,
        onError: (message: String?) -> Unit
    ) = flow {
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
        }.onError {
            onError("code: $statusCode, errorBody: $errorBody")
        }.onException {
            onError(message)
        }
    }.onCompletion {
        onComplete()
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