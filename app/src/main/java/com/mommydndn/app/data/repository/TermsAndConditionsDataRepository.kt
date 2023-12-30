package com.mommydndn.app.data.repository

/*
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
//        val approvalRequestList = termsItems.map {
//            UpdateTermsAndConditionsRequest(
//                termsId = it.termsId,
//                isApproved = it.isSelected
//            )
//        }
//        termsAndConditionsService.updateTermsApproval(approvalRequestList)
    }

}
 */
