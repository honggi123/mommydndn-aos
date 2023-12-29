package com.mommydndn.app.data.repository

import com.mommydndn.app.data.network.service.UserService
import com.mommydndn.app.domain.model.user.NearbyNeighborhoods
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.domain.model.user.NeighborhoodDistance
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.model.user.OAuthToken
import com.mommydndn.app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun getAccessToken(authCode: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(oAuthProvider: OAuthProvider, accessToken: String): OAuthToken {
        // userService.signIn(SignInRequest(oAuthProvider.name, accessToken))
        TODO("Not yet implemented")
    }

    override fun getNeighborhood(): Flow<Neighborhood> {
        TODO("Not yet implemented")
    }

    override fun getNearbyNeighborhoodDistance(): Flow<NeighborhoodDistance> {
        TODO("Not yet implemented")
    }

    override fun getNearbyNeighborhoods(
        latitude: Double,
        longitude: Double
    ): Flow<NearbyNeighborhoods> {
        TODO("Not yet implemented")
    }
}