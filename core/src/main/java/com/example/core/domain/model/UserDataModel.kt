package com.example.core.domain.model

import android.os.Parcelable
import com.example.core.data.response.UserDataResponse
import io.reactivex.functions.Function
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDataModel(
    val name: String,
    val dateOfBirth: String,
    val address: String
) : Parcelable {

    class Mapper : Function<List<UserDataResponse>, List<UserDataModel>> {
        override fun apply(t: List<UserDataResponse>): List<UserDataModel> {
            return t.map {
                UserDataModel(
                    name = it.name.orEmpty(),
                    dateOfBirth = it.createdAt.orEmpty(),
                    address = "${it.addressNo} ${it.street} ${it.county} ${it.zipCode} ${it.country}"
                )
            }
        }

    }

}
