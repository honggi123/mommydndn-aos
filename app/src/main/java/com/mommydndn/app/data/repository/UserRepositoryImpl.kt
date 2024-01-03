package com.mommydndn.app.data.repository

import android.util.Log
import com.mommydndn.app.data.network.model.request.UpdateProfileImageRequest
import com.mommydndn.app.data.network.service.CommonService
import com.mommydndn.app.data.network.service.UserService
import com.mommydndn.app.domain.model.user.NearbyNeighborhoods
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.domain.model.user.NeighborhoodDistance
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.model.user.OAuthToken
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val commonService: CommonService,
) : UserRepository {

    fun updateProfileImage(imagePart: MultipartBody.Part): Flow<Unit> = flow<Unit> {
        val id = fetchImageId(imagePart)
        Log.e("id : ",id.toString() +"!")

        id?.let {
            userService.updateProfileImage(UpdateProfileImageRequest(imageId = id))
                /*
                .suspendOnSuccess {
                    emit(data)
                }.onError { Log.e("onError", message()) }
                    .onException { Log.e("onException", message()) }
                 */
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun fetchImageId(imagePart: MultipartBody.Part): Int? =
        withContext(Dispatchers.IO) {
            commonService.fetchImageResponse(image = imagePart).imageId
        }

    override suspend fun getAccessToken(authCode: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(oAuthProvider: OAuthProvider, accessToken: String): OAuthToken {
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

    override suspend fun signUp(
        oAuthProvider: OAuthProvider,
        accessToken: String,
        userType: UserType,
        neighborhoodId: Int
    ): OAuthToken {
        TODO("Not yet implemented")
    }
}