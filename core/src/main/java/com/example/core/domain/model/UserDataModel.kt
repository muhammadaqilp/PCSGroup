package com.example.core.domain.model

import android.os.Parcelable
import com.example.core.data.response.UserDataResponse
import io.reactivex.functions.Function
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDataModel(
    val avatar: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val address: String
) : Parcelable {

    class Mapper : Function<List<UserDataResponse>, List<UserDataModel>> {
        override fun apply(t: List<UserDataResponse>): List<UserDataModel> {
            return t.map {
                val parts = it.name?.trim()?.split("\\s+".toRegex())

                UserDataModel(
                    avatar = it.avatar.orEmpty(),
                    firstName = parts?.first().orEmpty(),
                    lastName = parts?.last().orEmpty(),
                    dateOfBirth = it.createdAt.orEmpty(),
                    address = "${it.addressNo} ${it.street} ${it.county} ${it.zipCode} ${it.country}"
                )
            }
        }

    }

}
