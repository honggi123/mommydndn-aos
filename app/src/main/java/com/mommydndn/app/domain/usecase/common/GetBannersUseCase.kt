package com.mommydndn.app.domain.usecase.common

import com.mommydndn.app.domain.model.banner.Banner
import com.mommydndn.app.domain.repository.CommonRepositoy
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetBannersUseCase @Inject constructor(
    courotineDispatcher: CoroutineDispatcher,
    private val repository: CommonRepositoy
) : UseCase<Unit, List<Banner>>(courotineDispatcher) {

    override suspend fun execute(parameters: Unit): List<Banner> {
        return repository.fetchBanners()
    }

}