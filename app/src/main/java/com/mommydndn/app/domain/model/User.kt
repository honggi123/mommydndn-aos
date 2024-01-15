package com.mommydndn.app.domain.model

data class User(
    val id: Int,
    val name: String,
    val displayName: String,
    val imageurl: String?,
)

enum class UserType {
    Individual, Agency
}