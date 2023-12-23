package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.user.NearbyNeighborhoods
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.domain.model.user.NeighborhoodDistance
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.model.user.OAuthToken
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getAccessToken(authCode: String): String

    // todo: store OAuthToken and get User
    suspend fun signIn(oAuthProvider: OAuthProvider, accessToken: String): OAuthToken

    // todo: parameters
    fun getNeighborhood(): Flow<Neighborhood>

    fun getNearbyNeighborhoodDistance(): Flow<NeighborhoodDistance>

    fun getNearbyNeighborhoods(latitude: Double, longitude: Double): Flow<NearbyNeighborhoods>
}