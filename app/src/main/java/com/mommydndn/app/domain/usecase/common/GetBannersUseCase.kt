package com.mommydndn.app.domain.usecase.common

import com.mommydndn.app.domain.model.banner.Banner
import com.mommydndn.app.domain.repository.CommonRepositoy
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetBannersUseCase @Inject constructor(
    private val repository: CommonRepositoy,
    private val courotineDispatcher: CoroutineDispatcher
) : UseCase<Unit, List<Banner>>(courotineDispatcher) {

    override suspend fun execute(parameters: Unit): List<Banner> {
        return repository.fetchBanners()
    }

}