package com.mommydndn.app.data.mapper

import com.mommydndn.app.data.network.model.NetworkOAuthProvider
import com.mommydndn.app.domain.model.OAuthProvider

fun OAuthProvider.toOAuthProvider(): NetworkOAuthProvider {
    return when (this) {
        OAuthProvider.Google -> NetworkOAuthProvider.GOOGLE
        OAuthProvider.Naver -> NetworkOAuthProvider.NAVER
        OAuthProvider.Kakao -> NetworkOAuthProvider.KAKAO
        // TODO add enum apple provider
    }
}