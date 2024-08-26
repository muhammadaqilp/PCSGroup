package com.example.core.data.repository

import com.example.core.data.service.UserApi
import com.example.core.domain.model.UserDataModel
import com.example.core.domain.repository.IUserRepository
import io.reactivex.Flowable
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi
) : IUserRepository {

    override fun getData(): Flowable<List<UserDataModel>> {
        return userApi.getData().map(UserDataModel.Mapper())
    }

}