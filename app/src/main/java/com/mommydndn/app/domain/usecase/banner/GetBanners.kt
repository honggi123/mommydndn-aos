package com.mommydndn.app.domain.usecase.banner

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.Banner
import com.mommydndn.app.domain.repository.CommonRepositoy
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBanners @Inject constructor(
    @IODispatcher courotineDispatcher: CoroutineDispatcher,
    private val repository: CommonRepositoy
) : UseCase<Unit, List<Banner>>(courotineDispatcher) {

    override suspend fun execute(parameters: Unit): List<Banner> {
        return repository.getBanners()
    }
}